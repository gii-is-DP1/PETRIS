package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.Colour.ColourService;
import org.springframework.samples.petclinic.model.PetrisBoardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.space.Space;
import org.springframework.samples.petclinic.space.SpaceService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final ColourService colourService;
    private final PetrisBoardService petrisBoardService;
    private final SpaceService spaceService;

    
    @Autowired
	public GameService(GameRepository gameRepository,PlayerService playerService,ColourService colourService,PetrisBoardService petrisBoardService,SpaceService spaceService) {
		this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.colourService = colourService;
        this.petrisBoardService = petrisBoardService;
        this.spaceService = spaceService;
	}
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public List<Game> getAllPlayingGames(){
        return gameRepository.findAllPlayingGames();
    }

    public List<Game> getAllFinishedGames(){
        return gameRepository.findAllPlayingGamesFinished();
    }

    public Game getGameById(Integer id){
        return gameRepository.findGameByid(id);        
    }
    public List<Game> getAllPublicActiveEmptyGames(){
        return gameRepository.findAllPublicActiveEmptyGames();
    }
    public Game getActiveGameByPlayer(String username){
        return gameRepository.findActiveGameByPlayer(username);
    }
    
    public Game getGameByCode(String gameCode) throws Exception {
        Game game = gameRepository.findGameByCode(gameCode);
        if(gameCode==null){
            throw new Exception();
        }else if(game!=null){
            return game;
        } else  {
            throw new InvalidCodeException();
        }
        
    }

    public Game save(Game g){
        return gameRepository.save(g);
    }

    public Game getGameByPlayerId(Integer id){
        return gameRepository.getGameByPlayerId(id);
    }

    public void setActiveGamesToFalse(String username){
        List<Player> playersList = this.playerService.getPlayersByUser(username);
            for (Player player : playersList){
                Game checkGame = this.getGameByPlayerId(player.getId());
                if (checkGame.isActive()){
                    checkGame.setActive(false);
                }
            }
    }

    public String generateCode(){
        String banco = "1234567890abcdefghijqlmnopqrstuvwxyzABCDEFGHIJQLMNOPQRSTUVWXYZ";
        String code = "";
        Integer lengthCode = 5;
        while (lengthCode !=0){
            Integer num = (int)(Math. random()*banco.length());
            char newChar = banco.charAt(num);
            code += newChar;     
            lengthCode--;   
        }
        return code;
    }

    public Game createGame(Game game, User user, String colourName, boolean isPublic){

            this.setActiveGamesToFalse(user.getUsername());
            
            Colour colour = this.colourService.getColourByName(colourName);
            Player player1 = new Player(colour,0,0,0, user);
            Player createdPlayer = this.playerService.save(player1);
            
            Game newGame = new Game();
            game.setCode(this.generateCode());
            game.setPublic(isPublic);
            game.setActive(true);
            game.setPlayer1(createdPlayer);
            game.createSpaces();
            BeanUtils.copyProperties(game, newGame, "id");
            Game createdGame = this.save(newGame);
            createdPlayer.setGame(createdGame);
            createdPlayer.setTurn(true);
            this.playerService.save(createdPlayer);
            for(Space space: createdGame.getSpaces()){
                space.setGame(createdGame);
                this.spaceService.save(space);
            }

            return createdGame;
    }

    public String fillGame(String gameCode, User user) throws Exception{

            Game game = this.getGameByCode(gameCode);

            String player1Colour = game.getPlayer1().getColour().getName();
            Colour randomColour = this.colourService.getOtherColoursExcept(player1Colour).get(0);

            if (game.getPlayer2()==null){
                
                Player player2 = new Player(randomColour, 0,0,0, user);
                Player createdPlayer = this.playerService.save(player2);
                game.setPlayer2(createdPlayer);
                Game createdGame = this.save(game);
                createdPlayer.setGame(createdGame);
                this.playerService.save(createdPlayer);

                this.petrisBoardService.createBoard(createdGame);
                
                return "redirect:/games/" + createdGame.getId();

            }else{
                throw new FullGameException();
            } 
    }

    //no puedes mover más bacterias de las que tienes en la casilla, ni mover a una casilla dejando mas de 5, ni dejando el mismo numero en algun disco
    public boolean permittedNumToMove(Space space1, Space space2, Integer numBacteriaToMove, String colour){

        boolean res = false;
        if (colour.equals("red")){
            res = space1.getNumRedBacteria()>=numBacteriaToMove && 
                            space2.getNumRedBacteria() + numBacteriaToMove <6 &&
                            ((space1.getNumRedBacteria() + space1.getNumRedSarcinas()*5 - numBacteriaToMove)==0 || 
                                (space1.getNumRedBacteria() + space1.getNumRedSarcinas()*5 - numBacteriaToMove)!= (space1.getNumBlackBacteria() + space1.getNumBlackSarcinas() *5)) &&
                            ((space2.getNumRedBacteria() + space2.getNumRedSarcinas()*5 + numBacteriaToMove)==0 ||
                                (space2.getNumRedBacteria() + space2.getNumRedSarcinas()*5 + numBacteriaToMove)!= (space2.getNumBlackBacteria() + space2.getNumBlackSarcinas() *5)) ;
        }else {
            res =  space1.getNumBlackBacteria()>=numBacteriaToMove  && 
                            space2.getNumBlackBacteria() + numBacteriaToMove <6 &&
                            ((space1.getNumBlackBacteria() + space1.getNumBlackSarcinas()*5 - numBacteriaToMove)==0 ||
                                (space1.getNumBlackBacteria() + space1.getNumBlackSarcinas()*5 - numBacteriaToMove) != (space1.getNumRedBacteria() + space1.getNumRedSarcinas() *5)) &&
                            ((space2.getNumBlackBacteria() + space2.getNumBlackSarcinas()*5 + numBacteriaToMove)==0 ||
                                (space2.getNumBlackBacteria() + space2.getNumBlackSarcinas()*5 + numBacteriaToMove)!= (space2.getNumRedBacteria() + space2.getNumRedSarcinas() *5)) ;
        }
        return res;
    }
    //se puede mover de una casilla a su adyacente
    public boolean isNeighbour(Space space1, Space space2){
        Integer position1 = space1.getPosition();
        Integer position2 = space2.getPosition();

        boolean res =   position1 == 7 || 
                        position2 ==7 || 
                        Math.abs(position1 - position2)==1 || 
                        (position1==1 && position2==6) || 
                        (position1==6 && position2==1);
        return res;
    }
    //no puedes mover a una casilla que tenga una sarcina tuya
    public boolean moveToSpaceWithoutSarcine(String colour, Space space){

        boolean res =   (colour == "red" && space.getNumRedSarcinas()<1) || 
                        (colour != "red" && space.getNumBlackSarcinas()<1);
        return res;
    }
    //comprobar si un jugador puede mover unas bacterias de una casilla a otra
    public boolean isMovementAllowed(Player player, Space space1, Space space2, Integer numBacteriaToMove){
        String colour = player.getColour().getName();

        boolean res =  player.isTurn() &&
                        moveToSpaceWithoutSarcine(colour, space2) && 
                        permittedNumToMove(space1, space2, numBacteriaToMove, colour) && 
                        isNeighbour(space1, space2);
        return res;
    }
   
   
    public void makeMove(String userName, Game activeGame, Integer space1Position, Integer space2Position, Integer numBacteriaToMove) throws  isPlayer1Exception, isPlayer2Exception, movementIsAllowedException {
        boolean isUserPlayer1 = this.playerService.isPlayerOfUser(activeGame.getPlayer1().getId(), userName);
        boolean isUserPlayer2 = this.playerService.isPlayerOfUser(activeGame.getPlayer2().getId(), userName);
        boolean isMovementAllowed = false; 
        Space space1 = null;
        Space space2 = null;

        for (Space space : activeGame.getSpaces()){
            if (space.getPosition() == space1Position){
                space1 = space;

            }else if(space.getPosition() == space2Position){
                space2 = space;
            }
        }

        if (isUserPlayer1){
            isMovementAllowed = isMovementAllowed(activeGame.getPlayer1(), space1, space2, numBacteriaToMove);
            
            if (isMovementAllowed){
                space1.move(true, activeGame.getPlayer1().getColour().getName(), numBacteriaToMove);
                space2.move(false, activeGame.getPlayer1().getColour().getName(), numBacteriaToMove);
                this.spaceService.save(space1);
                this.spaceService.save(space2);
    
            }
        }else if (isUserPlayer2){
            isMovementAllowed = isMovementAllowed(activeGame.getPlayer2(), space1, space2, numBacteriaToMove);
            if (isMovementAllowed){
                space1.move(true, activeGame.getPlayer2().getColour().getName(), numBacteriaToMove);
                space2.move(false, activeGame.getPlayer2().getColour().getName(), numBacteriaToMove);
                this.spaceService.save(space1);
                this.spaceService.save(space2);
    
            }
        }


    }
    public void changeTurn( String userName, Game activeGame) {
        boolean isUserPlayer1 = this.playerService.isPlayerOfUser(activeGame.getPlayer1().getId(), userName);
        boolean isUserPlayer2 = this.playerService.isPlayerOfUser(activeGame.getPlayer2().getId(), userName);


        if (isUserPlayer1){
            if(activeGame.getPlayer1().isTurn()){
                activeGame.getPlayer1().setTurn(false);
                activeGame.getPlayer2().setTurn(true);
            }
        }else if(isUserPlayer2){
            if(activeGame.getPlayer2().isTurn()){
                activeGame.getPlayer2().setTurn(false);
                activeGame.getPlayer1().setTurn(true);
            }
        }
        this.save(activeGame);
    }
   
    
}

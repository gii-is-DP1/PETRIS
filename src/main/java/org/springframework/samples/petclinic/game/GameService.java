package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.Colour.ColourService;
import org.springframework.samples.petclinic.model.PetrisBoard;
import org.springframework.samples.petclinic.model.PetrisBoardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.space.Space;
import org.springframework.samples.petclinic.space.SpaceService;
import org.springframework.samples.petclinic.token.ImpossibleMoveException;
import org.springframework.samples.petclinic.token.Token;
import org.springframework.samples.petclinic.token.TokenService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final ColourService colourService;
    private final PetrisBoardService petrisBoardService;
    private final SpaceService spaceService;
    private final TokenService tokenService;
 
    @Autowired
	public GameService(GameRepository gameRepository,TokenService tokenService,PlayerService playerService,ColourService colourService,PetrisBoardService petrisBoardService,SpaceService spaceService) {
		this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.tokenService = tokenService;
        this.colourService = colourService;
        this.petrisBoardService = petrisBoardService;
        this.spaceService = spaceService;
	}
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    
    public Page<Game> getAllPlayingGamesPage(Pageable page){
        return gameRepository.findAllPlayingGamesPage(page);
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
            Player player1 = new Player(colour,0,0,0,false,0,user);
            Player createdPlayer = this.playerService.save(player1);
            
            Game newGame = new Game();
            game.setCode(this.generateCode());
            game.setPublic(isPublic);
            game.setActive(true);
            game.setPlayer1(createdPlayer);
            game.createSpaces();
            game.setPhase(1);
            game.setRound(0);
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
                
                Player player2 = new Player(randomColour, 0,0,0,false,0, user);
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

        boolean res =   (colour.equals("red") && space.getNumRedSarcinas()<1) || 
                        (!colour.equals("red") && space.getNumBlackSarcinas()<1);
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
   
   //añadir una nueva bacteria por casilla en la fase de fision
    public void activateBinaryFision(Game game){
        String colourNotRed = "";
        if (game.getPlayer1().getColour().getName().equals("red")){
            colourNotRed= game.getPlayer2().getColour().getName();
        }else{
            colourNotRed = game.getPlayer1().getColour().getName();
        }
        for(Space space : game.getSpaces()){
            Integer numBlackBacteria = space.getNumBlackBacteria(); 
            Integer numRedBacteria = space.getNumRedBacteria();
            Integer numBlackSarcinas = space.getNumBlackSarcinas();
            Integer numRedSarcinas = space.getNumRedSarcinas();

            if (numRedSarcinas + numRedBacteria ==0){
                if (numBlackSarcinas<1 && numBlackBacteria>0){
                    if(numBlackBacteria<4){
                        space.setNumBlackBacteria(numBlackBacteria+1);
                        Integer petrisBoardId = this.petrisBoardService.getByGameId(game.getId()).getId();
                        Token token = this.tokenService.getTokenToAddInBinaryFision(petrisBoardId,colourNotRed,"bacterium");
                        token.setSpace(space);
                        token.setPositionInSpace(numBlackBacteria+1);
                        this.tokenService.save(token);
                    }else{

                        space.setNumBlackBacteria(0);
                        space.setNumBlackSarcinas(1);

                        List<Token> tokensToQuit = this.tokenService.getTokensToQuit(space);
                        for (Token token :tokensToQuit){
                            token.setPositionInSpace(0);
                            token.setSpace(null);
                            this.tokenService.save(token);
                        }

                        Integer petrisBoardId = this.petrisBoardService.getByGameId(game.getId()).getId();
                        Token token = this.tokenService.getTokenToAddInBinaryFision(petrisBoardId, colourNotRed,"sarcina");
                        token.setSpace(space);
                        token.setPositionInSpace(0);
                        this.tokenService.save(token);
                        
                    }
                }
            }else if(numBlackSarcinas + numBlackBacteria == 0){
                if (numRedSarcinas<1 && numRedBacteria>0){
                    if (numRedBacteria<4){
                        space.setNumRedBacteria(numRedBacteria+1);

                        Integer petrisBoardId = this.petrisBoardService.getByGameId(game.getId()).getId();
                        Token token = this.tokenService.getTokenToAddInBinaryFision(petrisBoardId,"red","bacterium");
                        token.setSpace(space);
                        token.setPositionInSpace(numRedBacteria+1);
                        this.tokenService.save(token);
                    }else{
                        space.setNumRedBacteria(0);
                        space.setNumRedSarcinas(1);

                        List<Token> tokensToQuit = this.tokenService.getTokensToQuit(space);
                        for (Token token :tokensToQuit){
                            token.setPositionInSpace(0);
                            token.setSpace(null);
                            this.tokenService.save(token);
                        }

                        Integer petrisBoardId = this.petrisBoardService.getByGameId(game.getId()).getId();
                        Token token = this.tokenService.getTokenToAddInBinaryFision(petrisBoardId, "red","sarcina");
                        token.setSpace(space);
                        token.setPositionInSpace(0);
                        this.tokenService.save(token);
                    }
                }
            }
            this.spaceService.save(space);
        }

    }
    
    //quien es el ganador por puntos 
    public void setWinnerForPoints(Game game){
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        String winnerColour = "";
        String loserColour = "";
        String player1Colour = player1.getColour().getName();
        String player2Colour = player2.getColour().getName();
        Integer player1points = player1.getPoints();
        Integer player2points = player2.getPoints();
        if  (player1points > player2points){
            winnerColour = player1Colour;
            loserColour = player2Colour;
        }else if (player2points> player1points){
            winnerColour = player2Colour;
            loserColour = player1Colour;
        }else{
            Integer player1UsedSarcinas = player1.getUsedSarcinas();
            Integer player2UsedSarcinas = player2.getUsedSarcinas();
            if (player1UsedSarcinas<player2UsedSarcinas){
                winnerColour = player1Colour;
                loserColour = player2Colour;
            }else if(player1UsedSarcinas>player2UsedSarcinas){
                winnerColour = player2Colour;
                loserColour = player1Colour;
            }else{
                Integer player1UsedBacteria = player1.getUsedBacteria();
                Integer player2UsedBacteria = player2.getUsedBacteria();
                if(player1UsedBacteria<player2UsedBacteria){
                    winnerColour = player1Colour;
                    loserColour = player2Colour;
                }else if(player1UsedBacteria>player2UsedBacteria){
                    winnerColour = player2Colour;
                    loserColour = player1Colour;
                }
            }
        }
        game.setWinner(winnerColour);
        game.setLoser(loserColour);
        this.save(game);
    }
    
    //contar puntos de la fase de contaminacion de un player en concreto
    public boolean playerContaminationPhase(Game game, Player player){
        boolean res = false;
        String colour = player.getColour().getName();
        Integer points = 0;
        if (colour.equals("red")){
            for (Space space : game.getSpaces()){
                Integer redTokens = space.getNumRedBacteria() + space.getNumRedSarcinas()*5; 
                Integer blackTokens = space.getNumBlackBacteria() + space.getNumBlackSarcinas()*5;
                if (redTokens>0 && blackTokens>redTokens){
                    points++;
                }
            }
        }else{
            for (Space space : game.getSpaces()){
                Integer redTokens = space.getNumRedBacteria() + space.getNumRedSarcinas()*5; 
                Integer blackTokens = space.getNumBlackBacteria() + space.getNumBlackSarcinas()*5;
                if (blackTokens>0 && redTokens>blackTokens){
                    points++;
                }
            }
        }
        player.setPoints(player.getPoints()+points);
        this.playerService.save(player);
        if (player.getPoints()>=10){
            res = true;
        }
        return res;
    }
    
    //activar fase de contaminacion
    public boolean activateContaminationPhase(Game game){
        boolean res = false;
        boolean player1IsWinner = this.playerContaminationPhase(game, game.getPlayer1());
        boolean player2IsWinner = this.playerContaminationPhase(game, game.getPlayer2());

        if(player1IsWinner && player2IsWinner){
            res = true;
            this.setWinnerForPoints(game);

        }else if (player1IsWinner){
            res = true; 
            game.setWinner(game.getPlayer1().getColour().getName());
            game.setLoser(game.getPlayer2().getColour().getName());
            this.save(game);
        }else if (player2IsWinner){
            res = true; 
            game.setWinner(game.getPlayer2().getColour().getName());
            game.setLoser(game.getPlayer1().getColour().getName());
            this.save(game);
        }
        return res;

    }

    //realizar un movimiento
    public void makeMove(String userName, Game activeGame, Integer space1Position, Integer space2Position, Integer numBacteriaToMove)throws ImpossibleMoveException, IncompleteGameException{
        
        boolean gameIsComplete = activeGame.getPlayer1() != null && activeGame.getPlayer2() !=null;
        if (!gameIsComplete){
            throw new IncompleteGameException();
        }
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

            isMovementAllowed = this.isMovementAllowed(activeGame.getPlayer1(), space1, space2, numBacteriaToMove);
            
            if (isMovementAllowed){
                
                PetrisBoard petrisBoard = this.petrisBoardService.getByGameId(activeGame.getId());
                this.tokenService.moveTokens(space1, space2, numBacteriaToMove, activeGame.getPlayer1().getColour().getName(),petrisBoard.getId());
                    
                space1.move(true, activeGame.getPlayer1().getColour().getName(), numBacteriaToMove);
                space2.move(false, activeGame.getPlayer1().getColour().getName(), numBacteriaToMove);
                this.spaceService.save(space1);
                this.spaceService.save(space2);

                activeGame.getPlayer1().setHasMoved(true);
                this.playerService.save(activeGame.getPlayer1());
            }
            else{
                throw new ImpossibleMoveException();
            }

        }else if (isUserPlayer2){
        
            isMovementAllowed = isMovementAllowed(activeGame.getPlayer2(), space1, space2, numBacteriaToMove);
           
            if (isMovementAllowed){

                PetrisBoard petrisBoard = this.petrisBoardService.getByGameId(activeGame.getId());
                this.tokenService.moveTokens(space1, space2, numBacteriaToMove, activeGame.getPlayer2().getColour().getName(),petrisBoard.getId());
                    
                space1.move(true, activeGame.getPlayer2().getColour().getName(), numBacteriaToMove);
                space2.move(false, activeGame.getPlayer2().getColour().getName(), numBacteriaToMove);
                this.spaceService.save(space1);
                this.spaceService.save(space2);
                    
                activeGame.getPlayer2().setHasMoved(true);
                this.playerService.save(activeGame.getPlayer2());
            }else{
                throw new ImpossibleMoveException();
            }
        }


    }
    
    //cambiar el turno
    public void changeTurn(String userName, Game activeGame) {
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
    
    //pasar de ronda
    public String passRound(String userName, Game activeGame) throws NotHisTurnException, NoMoveException{
        String res = "redirect:/games/" + activeGame.getId();
        boolean isPermitted = false;
        boolean isPlayer1 = this.playerService.isPlayerOfUser(activeGame.getPlayer1().getId(), userName);
        boolean isPlayer2 = this.playerService.isPlayerOfUser(activeGame.getPlayer2().getId(), userName);

        if (isPlayer1){
            if(!activeGame.getPlayer1().isTurn()){
                throw new NotHisTurnException();
            }else if(!activeGame.getPlayer1().isHasMoved()){
                throw new NoMoveException();
            }else{
                isPermitted = true;
            }
        }else if(isPlayer2){
            if(!activeGame.getPlayer2().isTurn()){
                throw new NotHisTurnException();
            }else if(!activeGame.getPlayer2().isHasMoved()){
                throw new NoMoveException();
            }else{
                isPermitted = true;
            }
        }

        if(isPermitted){

            Integer round = activeGame.getRound();
            Integer phase = activeGame.getPhase();

            boolean thereIsAWinner = false;
    
            if (phase == 1 || phase == 3 || phase == 5){
                activeGame.setPhase(phase + 1);
                changeTurn(userName, activeGame);
            }else if (phase == 2 || phase == 4){
                activeGame.setPhase(phase + 1);
                this.activateBinaryFision(activeGame);
    
            }else {
                this.activateBinaryFision(activeGame);
                thereIsAWinner = this.activateContaminationPhase(activeGame);
                            
                activeGame.setPhase(1);
                activeGame.setRound(round + 1);
            }
            this.save(activeGame);

            thereIsAWinner = thereIsAWinner || !this.checkThereIsPossibleMove(activeGame);

            if (thereIsAWinner){
                res += "/finishedGame";
    
            }else if (round == 4){
                this.setWinnerForPoints(activeGame);
                res += "/finishedGame";
            }
            this.tokenService.setAllHasBeenUsedToFalse(activeGame);
            this.playerService.setFalsePlayersMove(activeGame);
        }
    return res; 
   }

    private boolean checkThereIsPossibleMove(Game game) {
        Player player = null;
        boolean thereIsAMove = false;
        String winnerColour = "";
        String loserColour= "";
        String player1Colour = game.getPlayer1().getColour().getName();
        String player2Colour = game.getPlayer2().getColour().getName();
        if(game.getPlayer1().isTurn()){
            player = game.getPlayer1();
            winnerColour = player2Colour;
            loserColour = player1Colour;
        }else{
            player = game.getPlayer2();
            winnerColour = player1Colour;
            loserColour = player2Colour;
        }
        List<Space> spaces = game.getSpaces();

        for (Space spaceSource:spaces){
            for(Space spaceTarget:spaces){
                Integer numBacteriaToMove = 4;
                while(numBacteriaToMove!=0 && !thereIsAMove){
                    thereIsAMove = this.isMovementAllowed(player, spaceSource, spaceTarget, numBacteriaToMove);
                    numBacteriaToMove--;
                }
            }
        }
        if (!thereIsAMove){
            game.setWinner(winnerColour);
            game.setLoser(loserColour);
            this.save(game);
        }

        return thereIsAMove;
    }
    public boolean showPassTurnButton(Game activeGame, String userName) {

        boolean res = false ;
        boolean isPlayer1 = this.playerService.isPlayerOfUser(activeGame.getPlayer1().getId(), userName);
        boolean isPlayer2 = this.playerService.isPlayerOfUser(activeGame.getPlayer2().getId(), userName);

        if (isPlayer1){
            if(!activeGame.getPlayer1().isTurn()){
                res = true;
            }
        }else if(isPlayer2){
            if(!activeGame.getPlayer2().isTurn()){
                res = true;
            }
        }
        return res;
    }
    
}

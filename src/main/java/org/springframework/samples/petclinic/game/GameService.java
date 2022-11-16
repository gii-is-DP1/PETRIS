package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.space.Space;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    
    @Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game getGameById(Integer id){
        return gameRepository.findGameByid(id);        
    }
    public List<Game> getAllActiveGames(){
        return gameRepository.findAllActiveGames();
    }
    public Game getActiveGameByPlayer(String username){
        return gameRepository.findActiveGameByPlayer(username);
    }

    public Game save(Game g){
        return gameRepository.save(g);
    }
    public Game getGameByPlayerId(Integer id){
        return gameRepository.getGameByPlayerId(id);
    }

    //no puedes mover más bacterias de las que tienes en la casilla, ni mover a una casilla dejando mas de 5, ni dejando el mismo numero en algun disco
    public boolean permittedNumToMove(Space space1, Space space2, Integer numBacteriaToMove, String colour){

        boolean res = false;
        if (colour =="red"){
            res = space1.getNumRedBacteria()>=numBacteriaToMove && 
                            space2.getNumRedBacteria() + numBacteriaToMove <6 &&
                            (space1.getNumRedBacteria() + space1.getNumRedSarcinas()*5 - numBacteriaToMove) != (space1.getNumBlackBacteria() + space1.getNumBlackSarcinas() *5) &&
                            (space2.getNumRedBacteria() + space2.getNumRedSarcinas()*5 + numBacteriaToMove) != (space2.getNumBlackBacteria() + space2.getNumBlackSarcinas() *5) ;
        }else {
            res =  space1.getNumBlackBacteria()>=numBacteriaToMove  && 
                            space2.getNumBlackBacteria() + numBacteriaToMove <6 &&
                            (space1.getNumBlackBacteria() + space1.getNumBlackSarcinas()*5 - numBacteriaToMove) != (space1.getNumRedBacteria() + space1.getNumRedSarcinas() *5) &&
                            (space2.getNumBlackBacteria() + space2.getNumBlackSarcinas()*5 + numBacteriaToMove) != (space2.getNumRedBacteria() + space2.getNumRedSarcinas() *5) ;
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
                        (colour == "black" && space.getNumBlackSarcinas()<1);
        return res;
    }
    //comprobar si un jugador puede mover unas bacterias de una casilla a otra
    public boolean isMovementAllowed(Player player, Space space1, Space space2, Integer numBacteriaToMove){
        String colour = player.getColour().getName();

        boolean res =  moveToSpaceWithoutSarcine(colour, space2) && 
                        permittedNumToMove(space1, space2, numBacteriaToMove, colour) && 
                        isNeighbour(space1, space2);
        return res;
    }
    
}

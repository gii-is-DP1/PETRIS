package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.space.Space;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @Column(name = "time")
    private Integer time; 

    @Column(name = "round")
    @Range(min=0, max=4)
    private Integer round;

    @Column(name = "winner")
    private String winner;

    @Column(name = "loser")
    private String loser;

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "phase_type_id")
    private PhaseType phaseType;

    @OneToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2_id")
    private Player player2;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Space> spaces;

    public void createSpaces(){
        Integer numSpaces = 7;
        List<Space> spacesToAdd = new ArrayList<>();
        while(numSpaces!=0){
            Space newSpace = new Space();
            newSpace.setPosition(numSpaces);
            newSpace.setNumBlackBacteria(0);
            newSpace.setNumBlackSarcinas(0);
            newSpace.setNumRedBacteria(0);
            newSpace.setNumRedSarcinas(0);
            spacesToAdd.add(newSpace);
        }
        this.spaces = spacesToAdd;
    }
    //no puedes mover más bacterias de las que tienes en la casilla 
    public boolean permittedNumToMove(Space space, Integer numBacteriaToMove, String colour){
        boolean res =   (colour =="red" && space.getNumRedBacteria()>=numBacteriaToMove) || 
                        (colour == "black" && space.getNumBlackBacteria()>=numBacteriaToMove);
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
    //no se puede mover de una casilla a otra si se deja una casilla con el mismo numero de fichas rojas y negras
    public boolean numBacteriaInSpaceOk(Space space){
        
        boolean res = (space.getNumBlackBacteria() + space.getNumBlackSarcinas()*5) != (space.getNumRedBacteria() + space.getNumRedSarcinas() *5);
        
        return res;
    }
    //no puedes mover a una casilla que tenga una sarcina tuya
    public boolean moveToSpaceWithoutSarcine(String colour, Space space){

        boolean res =   (colour == "red" && space.getNumRedSarcinas()<1) || 
                        (colour == "black" && space.getNumBlackSarcinas()<1);
        return res;
    }
    //comprobar si un jugador puede mover unas bacterias de una casilla a otra
    public boolean movementAllowed(Player player, Space space1, Space space2, Integer numBacteriaToMove){
        String colour = player.getColour().getName();

        boolean res =  moveToSpaceWithoutSarcine(colour, space2) && 
                        permittedNumToMove(space1, numBacteriaToMove, colour) && 
                        isNeighbour(space1, space2) && 
                        numBacteriaInSpaceOk(space1) && 
                        numBacteriaInSpaceOk(space2);
        return res;
    }
    //añadir una nueva bacteria por casilla en la fase de fision
    public void activateBinaryFision(){
        for(Space space : this.spaces){
            Integer numBlackBacteria = space.getNumBlackBacteria(); 
            Integer numRedBacteria = space.getNumRedBacteria();
            Integer numBlackSarcinas = space.getNumBlackSarcinas();
            Integer numRedSarcinas = space.getNumRedSarcinas();

            if (numRedSarcinas*5 + numRedBacteria ==0){
                if (numBlackSarcinas<1 && numBlackBacteria>0){
                    if(numBlackBacteria<4){
                        space.setNumBlackBacteria(numBlackBacteria+1);
                    }else{
                        space.setNumBlackBacteria(0);
                        space.setNumBlackSarcinas(1);
                    }
                }
            }else if(numBlackSarcinas*5 + numBlackBacteria == 0){
                if (numRedSarcinas<1 && numRedBacteria>0){
                    if (numRedBacteria<4){
                        space.setNumRedBacteria(numRedBacteria+1);
                    }else{
                        space.setNumRedBacteria(0);
                        space.setNumRedSarcinas(1);
                    }
                }
            }
        }
    }
    //contar puntos de una ronda (puntos que gana un jugador)
    public Integer countContaminationPoints(Player player){
        String colour = player.getColour().getName();
        Integer points = 0;
        if (colour =="red"){
            for (Space space : this.spaces){
                Integer redTokens = space.getNumRedBacteria() + space.getNumRedSarcinas(); 
                Integer blackTokens = space.getNumBlackBacteria() + space.getNumBlackSarcinas();
                if (redTokens>0 && blackTokens>redTokens){
                    points++;
                }
            }
        }else{
            for (Space space : this.spaces){
                Integer redTokens = space.getNumRedBacteria() + space.getNumRedSarcinas(); 
                Integer blackTokens = space.getNumBlackBacteria() + space.getNumBlackSarcinas();
                if (blackTokens>0 && redTokens>blackTokens){
                    points++;
                }
            }
        }
        return points;
    }
}

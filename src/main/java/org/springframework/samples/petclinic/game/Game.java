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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    @Min(0)
    @Column(name = "time")
    private Integer time; 

    @Min(0)
    @Max(4)
    @Column(name = "round")
    private Integer round;

    @Column(name = "winner")
    private String winner;

    @Column(name = "loser")
    private String loser;

    @Column(name = "isActive")
    private boolean isActive;

    @NotNull
    @Column(name = "isPublic")
    private boolean isPublic;

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
    //contar puntos de la fase de contaminacion
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
        player.setPoints(player.getPoints()+points);
        return points;
    }
    //quien es el ganador al pasar las 4 rondas
    public String getWinnerLastRound(Player player1, Player player2){
        String winnerColour ="";
        String player1Colour = player1.getColour().getName();
        String player2Colour = player2.getColour().getName();
        Integer player1points = player1.getPoints();
        Integer player2points = player2.getPoints();
        if  (player1points > player2points){
            winnerColour = player1Colour;
        }else if (player2points> player1points){
            winnerColour = player2Colour;
        }else{
            Integer player1UsedSarcinas = player1.getUsedSarcinas();
            Integer player2UsedSarcinas = player2.getUsedSarcinas();
            if (player1UsedSarcinas<player2UsedSarcinas){
                winnerColour = player1Colour;
            }else if(player1UsedSarcinas>player2UsedSarcinas){
                winnerColour = player2Colour;
            }else{
                Integer player1UsedBacteria = player1.getUsedBacteria();
                Integer player2UsedBacteria = player2.getUsedBacteria();
                if(player1UsedBacteria<player2UsedBacteria){
                    winnerColour = player1Colour;
                }else if(player1UsedBacteria>player2UsedBacteria){
                    winnerColour = player2Colour;
                }
            }
        }
        this.winner = winnerColour;
        return winnerColour + "player is the winner";
    }
}

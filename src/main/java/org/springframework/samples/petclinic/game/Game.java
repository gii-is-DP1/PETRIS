package org.springframework.samples.petclinic.game;

import java.time.LocalTime;
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

    private LocalTime startGameTime = LocalTime.now();

    private Integer phase;

    @OneToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2_id")
    private Player player2;

    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Space> spaces;

    public Space getFirstSpace(){
        Space spaceToReturn = null;
        for (Space space : this.spaces){
            if (space.getPosition()==1){
                spaceToReturn = space;
            }
        }
        return spaceToReturn;
    }
    public Space getFourthSpace(){
        Space spaceToReturn = null;
        for (Space space : this.spaces){
            if (space.getPosition()==4){
                spaceToReturn = space;
            }
        }
        return spaceToReturn;
    }

    public void createSpaces(){
        Integer numSpaces = 7;
        List<Space> spacesToAdd = new ArrayList<>();
        while(numSpaces!=0){
            if (numSpaces == 1){
                Space newSpace = new Space();
                newSpace.setPosition(numSpaces);
                newSpace.setNumBlackBacteria(0);
                newSpace.setNumBlackSarcinas(0);
                newSpace.setNumRedBacteria(1);
                newSpace.setNumRedSarcinas(0);
                spacesToAdd.add(newSpace);
                numSpaces--;
            }else if(numSpaces == 4){
                Space newSpace = new Space();
                newSpace.setPosition(numSpaces);
                newSpace.setNumBlackBacteria(1);
                newSpace.setNumBlackSarcinas(0);
                newSpace.setNumRedBacteria(0);
                newSpace.setNumRedSarcinas(0);
                spacesToAdd.add(newSpace);
                numSpaces--;
            }else{
                Space newSpace = new Space();
                newSpace.setPosition(numSpaces);
                newSpace.setNumBlackBacteria(0);
                newSpace.setNumBlackSarcinas(0);
                newSpace.setNumRedBacteria(0);
                newSpace.setNumRedSarcinas(0);
                spacesToAdd.add(newSpace);
                numSpaces--;
            }
        }
        this.spaces = spacesToAdd;
    }
    
    
    


}

package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends BaseEntity{

    private boolean isTurn;

    @ManyToOne
    private Colour colour;

    @NotNull
    private Integer usedBacteria;

    @NotNull
    private Integer usedSarcinas;

    @NotNull
    private Integer contaminationPoints;

    private Integer points;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Player(Colour colour, Integer usedBacteria,Integer usedSarcinas,Integer contaminationPoints, User user){
      this.colour = colour;
      this.usedBacteria = usedBacteria;
      this.usedSarcinas = usedSarcinas;
      this.contaminationPoints = contaminationPoints;
      this.user = user;
    }

    public Player() {
    }


}

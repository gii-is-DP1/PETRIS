package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.token.Colour;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends BaseEntity{

    @NotNull
    private boolean turn;

    @NotNull
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
    @NotNull
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;



    /*
     * public Token getTokenwithIdDifferent(Object type, Integer id) {
        type = ((String) type).toLowerCase();
		for (Token token : getTokensInternal()) {
			String compType = (String) token.getType();
			compType = compType.toLowerCase();
			if (compType.equals(type) && token.getId()!=id) {
				return token;
			}
		}
		return null;
    }
     */

}

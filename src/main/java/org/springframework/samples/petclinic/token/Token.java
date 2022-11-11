package org.springframework.samples.petclinic.token;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.space.Space;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tokens")
public class Token extends BaseEntity{
      
	@NotNull
	@ManyToOne
	private TokenType tokenType;
       
	@NotNull
	@ManyToOne
	private Colour colour;

	@ManyToOne(optional = true)
	private Space space;

	@ManyToOne
	@NotNull
	private Player player;

}

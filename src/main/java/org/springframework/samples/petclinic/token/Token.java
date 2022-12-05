package org.springframework.samples.petclinic.token;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.PetrisBoard;
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

	private boolean hasBeenUsed;
       
	@NotNull
	@ManyToOne
	private Colour colour;

	@ManyToOne(optional = true)
	private Space space;

	private Integer positionInSpace;

	@ManyToOne
	@NotNull
	private Player player;

	@ManyToOne
	private PetrisBoard petrisBoard;

	public Integer getXPosition(){
		Integer x = -100;
		if (this.space != null){
			Integer numSpace = this.space.getPosition();

			if (this.tokenType.getName().equals("bacterium")){
				if (numSpace == 1) {
					x = 40;
				}else if(numSpace == 2 || numSpace == 6){
					x = 240;
				}else if(numSpace == 3 || numSpace == 5){
					x = 550;
				}else if(numSpace == 4){
					x = 760;
				}else if(numSpace == 7){
					x = 400;
				}
				return x + this.getPositionInSpace() * 25;
			}else{
				if (numSpace == 1) {
					x = 60;
				}else if(numSpace == 2 || numSpace == 6){
					x = 260;
				}else if(numSpace == 3 || numSpace == 5){
					x = 430;
				}else if(numSpace == 4){
					x = 580;
				}else if(numSpace == 7){
					x = 790;
				}
				return x ;
				
			}
		}
		return x ;
	}
	public Integer getYPosition(){
		Integer y = 0;
		if (this.space != null){
			Integer numSpace = this.space.getPosition();
			String colourName = this.colour.getName();
			if ((numSpace == 2 || numSpace ==3)) {
				if( colourName.equals("red")){
					y = 185;
				}else{
					y = 250;
				}
			}else if(numSpace == 1 || numSpace == 4 || numSpace == 7){
				if( colourName.equals("red")){
					y = 400;
				}else{
					y = 475;
				}
			}else if((numSpace == 5 || numSpace ==6)){
				if( colourName.equals("red")){
					y = 650;
				}else{
					y = 730;
				}
			}
		}
		return y;
	}
}
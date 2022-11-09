package org.springframework.samples.petclinic.token;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.space.Space;

@Entity
@Table(name = "tokens")
public class Token extends BaseEntity{

    @Column(name= "token_type")       
	@NotNull
	private TokenType tokenType;

	@Column(name = "colour")        
	@NotNull
	private Colour colour;

	@OneToOne(optional = true)
	private Space space;
}

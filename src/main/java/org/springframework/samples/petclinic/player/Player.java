package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.token.Colour;
import org.springframework.samples.petclinic.token.Token;

@Entity
public class Player extends BaseEntity{

    @NotNull
    private String turn;

    @NotNull
    private Colour colour;

    private boolean isWinner;

    @NotNull
    private Integer usedBacteria;

    @NotNull
    private Integer usedSarcinas;

    @NotNull
    private Integer contaminationPoints;

    @OneToMany
    private Token token; 
}

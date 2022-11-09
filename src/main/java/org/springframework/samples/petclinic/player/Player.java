package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.tomcat.jni.User;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.token.Colour;


@Entity
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

}

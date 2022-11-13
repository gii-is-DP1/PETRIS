package org.springframework.samples.petclinic.space;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "spaces")
public class Space extends BaseEntity{

    @NotNull
    @Min(1)
    @Max(7)
    private Integer position;

    @NotNull
    private Integer numBlackBacteria;

    @NotNull
    private Integer numRedBacteria;

    @NotNull
    private Integer numBlackSarcinas;

    @NotNull
    private Integer numRedSarcinas;

    @ManyToOne
    private Game game;


    public static Space createSpace(Integer position, Integer numBlackBacteria, Integer numRedBacteria, Integer numBlackSarcinas, Integer numRedSarcinas ){
        Space newSpace = new Space();
        newSpace.setPosition(position);
        newSpace.setNumBlackBacteria(numBlackBacteria);
        newSpace.setNumBlackSarcinas(numBlackSarcinas);
        newSpace.setNumRedBacteria(numRedBacteria);
        newSpace.setNumRedSarcinas(numRedSarcinas);

        return newSpace;
    }
}
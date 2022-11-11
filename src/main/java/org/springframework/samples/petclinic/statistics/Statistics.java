package org.springframework.samples.petclinic.statistics;

import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class Statistics extends BaseEntity {
    /*
     @NotNull
     @DateTimeFormat(pattern = "mm:ss")
     LocalTime time;
     */

    @NotNull
    @DateTimeFormat(pattern = "hh:mm:ss")
    LocalTime initTime;

    @NotNull
    @DateTimeFormat(pattern = "hh:mm:ss")
    LocalTime endTime;

    @NotNull
    @Min(1)
    @Max(3)
    Integer finalRound;
    
    @NotNull
    Boolean winner;

    @NotNull
    @Min(0)
    @Max(20)
    Integer usedBacteries;

    @NotNull
    @Max(4)
    @Min(0)
    Integer usedSarcines;

    @NotNull
    Integer gamePoints;

    @DurationMax(minutes = 25)
    @DurationMin(minutes = 0)
    Duration time() {
        return Duration.between(initTime, endTime);
    }

    @OneToOne
    @JoinColumn(name = "game_id")
    Game game;
}

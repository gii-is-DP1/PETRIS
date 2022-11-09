package org.springframework.samples.petclinic.game;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @Column(name = "time")
    private LocalDateTime time; 

    @NotNull
    @Column(name = "round")
    @Range(min=0, max=4)
    private Integer round;

    @Column(name = "winner_id")
    private Player winner;

    @Column(name = "loser_id")
    private Player loser;

    @NotNull
    @Column(name = "phase_id")
    private PhaseType phase;

    @NotNull
    @OneToMany
    @JoinColumn(name="player1_id")
    private Player player1;

    @NotNull
    @OneToMany
    @JoinColumn(name="player2_id")
    private Player player2;

}

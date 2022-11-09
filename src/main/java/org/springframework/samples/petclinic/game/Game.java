package org.springframework.samples.petclinic.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private Integer time; 

    @Column(name = "round")
    @Range(min=0, max=4)
    private Integer round;

    @Column(name = "winner")
    private String winner;

    @Column(name = "loser")
    private String loser;

    @ManyToOne
    @JoinColumn(name = "phase_type_id")
    private PhaseType phaseType;

    @OneToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2_id")
    private Player player2;
    

}

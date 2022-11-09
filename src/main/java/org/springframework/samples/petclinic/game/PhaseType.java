package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phase_types")
public class PhaseType extends NamedEntity{
    
}

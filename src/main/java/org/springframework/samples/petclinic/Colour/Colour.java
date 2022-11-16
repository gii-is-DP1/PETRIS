package org.springframework.samples.petclinic.Colour;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "colours")
public class Colour extends BaseEntity{

    @NotNull
    String name; 

}

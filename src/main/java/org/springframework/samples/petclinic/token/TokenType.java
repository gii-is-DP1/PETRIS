package org.springframework.samples.petclinic.token;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "token_types")
public class TokenType extends BaseEntity{

    @NotNull
    String name; 

}
package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.token.Token;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PetrisBoard extends BaseEntity{
    
    String background;
    @Positive
    int width;
    @Positive
    int height;

    public PetrisBoard(){
        this.background="../resources/images/fondo.png";
        this.width=1000;
        this.height=1000;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "petrisBoard",fetch = FetchType.EAGER)
    List<Token> tokens; 
}

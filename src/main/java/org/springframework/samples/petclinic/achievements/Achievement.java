package org.springframework.samples.petclinic.achievements;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Achievement extends NamedEntity {
    
    @NotBlank
    private String description;
    private String badgeImage;
    @Min(0)
    private double threshold;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    Metric metric;

    @ManyToMany     
    @JoinTable(name = "usersAchievement", joinColumns = @JoinColumn(name = "achievement_id"))     
    public List<User> usersWithAchievement;

    public String getActualDescription(){
        return description.replace("<THRESHOLD>",String.valueOf(threshold));
    }
    
}

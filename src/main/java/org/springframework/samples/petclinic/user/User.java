package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@NotNull
	@Id
	String username;

	@NotNull
	@Email
	String email;

	Integer points;

	@Column(name = "played_games")
	Integer playedGames;

	@Column(name = "won_games")
	Integer wonGames;

	@Column(name = "lost_games")
	Integer lostGames;

	@NotNull
	@Size(min = 5)
	String password;

	boolean enabled;

	Double winrate() {
		return wonGames*1.0/playedGames;
	}
	
	public boolean isNew() {
		return this.username == null;
	}

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
	
}

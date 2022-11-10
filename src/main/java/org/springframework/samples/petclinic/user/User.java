package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class User extends BaseEntity {
	@NotNull
	String username;

	@NotNull
	@Email
	String email;

	@NotNull
	@Min(0)
	Integer points;

	@NotNull
	@Min(0)
	@Column(name = "played_games")
	Integer playedGames;


	@NotNull
	@Min(0)
	@Column(name = "winned_games")
	Integer winnedGames;

	@NotNull
	@Min(0)
	@Column(name = "losed_games")
	Integer losedGames;

	@NotNull
	@Size(min = 5)
	String password;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
	
}

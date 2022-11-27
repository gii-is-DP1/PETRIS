package org.springframework.samples.petclinic.user;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.achievements.Achievement;
import org.springframework.samples.petclinic.player.Player;

import lombok.Builder;
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

	@Builder.Default
	Integer points = 0;

	@Column(name = "played_games")
	@Builder.Default
	Integer playedGames = 0;

	@Column(name = "won_games")
	@Builder.Default
	Integer wonGames = 0;

	@Column(name = "lost_games")
	@Builder.Default
	Integer lostGames = 0;

	@NotNull
	@Size(min = 5)
	String password;

	@ManyToMany
	private Set<Achievement> achievements;

	boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Player> players;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> friends;

	public User(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {
    }

    Double winrate() {
		return (double) Math.round(wonGames*1.0/playedGames);
	}

	public boolean isNew() {
		return this.username == null;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

	public Set<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(Set<Achievement> achievements) {
		this.achievements = achievements;
	}
	
}

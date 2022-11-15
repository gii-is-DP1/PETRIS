package org.springframework.samples.petclinic.friends;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invitacion_amigo")
public class FriendRequest extends BaseEntity{

    @OneToOne()
	@JoinColumn(name = "username1")
	private User user1;
	@OneToOne()
	@JoinColumn(name = "username2")
	private User user2;

}
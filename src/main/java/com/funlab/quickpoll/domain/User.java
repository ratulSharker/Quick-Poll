package com.funlab.quickpoll.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;
	

	@Column(name = "USERNAME")
	@NotEmpty
	private String username;
	
	
	@Column(name = "PASSWORD")
	@NotEmpty
	@JsonIgnore
	private String password;
	
	@Column(name = "FIRST_NAME")
	private String firstname;
	
	@Column(name = "LAST_NAME")
	private String lastname;
	
	@Column(name = "ADMIN")
	@NotEmpty
	private boolean admin;
	
}

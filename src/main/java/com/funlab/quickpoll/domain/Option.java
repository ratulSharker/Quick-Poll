package com.funlab.quickpoll.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPTION_ID")
	private Long id;

	@Column(name = "OPTION_VALUE")
	private String value;
	
	// @ManyToOne
	// @JoinColumn(name = "POLL_ID")
	// private Poll poll;
	
	@Column(name = "POLL_ID")
	private Long pollId;

	@Override
	public String toString() {
		return "Option Id : " + this.id + " ~ Value: " + this.value;
	}

}

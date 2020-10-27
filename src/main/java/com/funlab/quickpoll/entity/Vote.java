package com.funlab.quickpoll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vote {

	@Id
	@GeneratedValue
	@Column(name = "VOTE_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "OPTION_ID")
	private Option option;

	@Override
	public String toString() {
		return "Vote id: " + this.id + " option: " + this.option.getId() + " ~ " + this.option.getValue();
	}
}

package com.funlab.quickpoll.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Poll {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_ID")
	private Long id;

	@Column(name = "QUESTION")
	private String question;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "POLL_ID")
	@OrderBy
	private Set<Option> options;


	@Override
	public String toString() {
		return "Question id:" + this.id + " question: " + this.question;
	}

}

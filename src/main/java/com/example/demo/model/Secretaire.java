package com.example.demo.model;

import javax.persistence.*;



@Entity
@Table(name="secretaire")
public class Secretaire extends Personne {

	@Column(name="cin")
	private String cin;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
	
	public Secretaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Secretaire(String cin) {
		super();
		this.cin = cin;
	}
	
	
}
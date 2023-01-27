package com.example.demo.model;

import javax.persistence.*;


@Entity
@Table(name="patient")
public class Patient  extends Personne {
	

	@Column(name="cin")
	private String cin;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String cin) {
		super();
		this.cin = cin;
	}
	
	
}
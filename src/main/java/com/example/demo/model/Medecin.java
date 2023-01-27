package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="medecin")
public class Medecin extends Personne {

	

	@Column(name="specialite")
	private String specialite;
	@Column(name="principal")
	private long principal;
	
	
	public Medecin(String specialite, long principal) {
		super();
		this.specialite = specialite;
		this.principal = principal;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public long getPrincipal() {
		return principal;
	}

	public void setPrincipal(long principal) {
		this.principal = principal;
	}

	public Medecin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

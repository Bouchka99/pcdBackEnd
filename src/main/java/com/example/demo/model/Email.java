package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "email")
public class Email {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email")
	 private String email;
	
	@Column(name = "password")
	 private String password;
	
	@Column(name = "object")
	 private String object;
	
	@Column(name = "subject")
	 private String subject;
	
	@Column(name = "emaild")
	 private String emaild;
	
	
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Email(String email, String password, String object, String subject, String emaild) {
		super();
		this.email = email;
		this.password = password;
		this.object = object;
		this.subject = subject;
		this.emaild = emaild;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmaild() {
		return emaild;
	}
	public void setEmaild(String emaild) {
		this.emaild = emaild;
	}
}
package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name="rendezvous")
public class Rendezvous {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;

@Column(name="nom")
private String nom;

@Column(name="prenom")
private String prenom;

@Column(name="age")
private long age;

@Column(name="telephone")
private String telephone;

@Column(name="email")
private String email;

@Column(name="description")
private String description;

@Column(name="disponibilite1")
private String disponibilite1;

@Column(name="disponibilite2")
private String disponibilite2;

@Column(name="disponibilite3")
private String disponibilite3;

@Column(name="temps1")
private String temps1;

@Column(name="temps2")
private String temps2;

@Column(name="temps3")
private String temps3;

@Column(name="therapie")
private String therapie;

@Column(name="arrivee")
private String arrivee;

public String getArrivee() {
return arrivee;
}

public void setArrivee(String arrivee) {
this.arrivee = arrivee;
}

public long getId() {
return id;
}

public void setId(long id) {
this.id = id;
}

public String getNom() {
return nom;
}

public String getTherapie() {
return therapie;
}

public void setTherapie(String therapie) {
this.therapie = therapie;
}

public void setNom(String nom) {
this.nom = nom;
}

public String getPrenom() {
return prenom;
}

public void setPrenom(String prenom) {
this.prenom = prenom;
}

public long getAge() {
return age;
}

public void setAge(long age) {
this.age = age;
}

public String getTelephone() {
return telephone;
}

public void setTelephone(String telephone) {
this.telephone = telephone;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getDisponibilite1() {
return disponibilite1;
}

public void setDisponibilite1(String disponibilite1) {
this.disponibilite1 = disponibilite1;
}

public String getDisponibilite2() {
return disponibilite2;
}

public void setDisponibilite2(String disponibilite2) {
this.disponibilite2 = disponibilite2;
}

public String getDisponibilite3() {
return disponibilite3;
}

public void setDisponibilite3(String disponibilite3) {
this.disponibilite3 = disponibilite3;
}

public String getTemps1() {
return temps1;
}

public void setTemps1(String temps1) {
this.temps1 = temps1;
}

public String getTemps2() {
return temps2;
}

public void setTemps2(String temps2) {
this.temps2 = temps2;
}

public String getTemps3() {
return temps3;
}

public void setTemps3(String temps3) {
this.temps3 = temps3;
}


public Rendezvous(String nom, String prenom, long age, String telephone, String email, String description,
String disponibilite1, String disponibilite2, String disponibilite3, String temps1, String temps2,
String temps3, String therapie, String arrivee) {
super();
this.nom = nom;
this.prenom = prenom;
this.age = age;
this.telephone = telephone;
this.email = email;
this.description = description;
this.disponibilite1 = disponibilite1;
this.disponibilite2 = disponibilite2;
this.disponibilite3 = disponibilite3;
this.temps1 = temps1;
this.temps2 = temps2;
this.temps3 = temps3;
this.therapie = therapie;
this.arrivee = arrivee;
}

public Rendezvous() {
super();
// TODO Auto-generated constructor stub
}





}
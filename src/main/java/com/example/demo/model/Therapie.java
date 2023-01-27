package com.example.demo.model;



import javax.persistence.*;


@Entity
@Table(name="therapie")
public class Therapie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="nom")
	private String nom;
	



	@Column(name=" ratefive")
	private long  ratefive;
	
	@Column(name="ratefour")
	private long ratefour;
	
	@Column(name="ratethree")
	private long ratethree;
	
	@Column(name="ratetwo")
	private long ratetwo;
	
	@Column(name="rateone")
	private long rateone;
	
	@Column(name="starRating")
	private long starRating;
	
	@Column(name="rateall")
	private long rateall;
	
	public long getRatefive() {
		return ratefive;
	}
	public void setRatefive(long ratefive) {
		this.ratefive = ratefive;
	}
	public long getRatefour() {
		return ratefour;
	}
	public void setRatefour(long ratefour) {
		this.ratefour = ratefour;
	}
	public long getRatethree() {
		return ratethree;
	}
	public void setRatethree(long ratethree) {
		this.ratethree = ratethree;
	}
	public long getRatetwo() {
		return ratetwo;
	}
	public void setRatetwo(long ratetwo) {
		this.ratetwo = ratetwo;
	}
	public long getRateone() {
		return rateone;
	}
	public void setRateone(long rateone) {
		this.rateone = rateone;
	}
	public long getStarRating() {
		return starRating;
	}
	public void setStarRating(long starRating) {
		this.starRating = starRating;
	}
	public long getRateall() {
		return rateall;
	}
	public void setRateall(long rateall) {
		this.rateall = rateall;
	}



	@Column(name="desription")
	private String description;
	

	@Column(name="image")
	private String image;
	

	
	@Column(name="video")
	private String video;
	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public Therapie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	//
	public Therapie(String nom, long ratefive, long ratefour, long ratethree, long ratetwo, long rateone,
			long starRating, long rateall, String description, String image, String video) {
		super();
		this.nom = nom;
		this.ratefive = ratefive;
		this.ratefour = ratefour;
		this.ratethree = ratethree;
		this.ratetwo = ratetwo;
		this.rateone = rateone;
		this.starRating = starRating;
		this.rateall = rateall;
		this.description = description;
		this.image = image;
		this.video = video;
		
	}
	//

}
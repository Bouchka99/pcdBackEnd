package com.example.demo.model;
import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	/*public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}*/
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;

	}


}

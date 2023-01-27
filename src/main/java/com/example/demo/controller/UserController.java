package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Medecin;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

//@CrossOrigin(origins="https://cabinet-med.netlify.app")
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserRepository userRepository ;
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		System.out.println(user);
		return userRepository.save(user);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAll")
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	@CrossOrigin(origins="http://localhost:4200")	
	@GetMapping("/find/{email}")
	public ResponseEntity<User> findUserByEmail(@PathVariable String email ){
		User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException ("User non trouvé avec l'email :"+email));
		return ResponseEntity.ok(user);
	}
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Médecin non trouvé avec l'id :"+id));
		return ResponseEntity.ok(user);
	}
	//update médecin
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userInfo){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));

		user.setEmail(userInfo.getEmail());
		user.setPassword(userInfo.getPassword());
		user.setRole(userInfo.getRole());

		
		User userAjour = userRepository.save(user);
		return ResponseEntity.ok(userAjour);
	}
	
	//delete médecin
	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id){
		
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));
		userRepository.delete(user);
		//retourne void c'est pour cela qu'on a créer the map pour retourner une réponse
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
		
	}
	
	
	//login
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/login")
	public User login(@RequestBody User user) throws Exception {

		String emaiId = user.getEmail();
		String passwd = user.getPassword();
		User userObj = null;
		if(emaiId != null && passwd != null) {
			userObj = userRepository.findByEmailAndPassword(emaiId, passwd);
			userObj = userRepository.getByEmailAndPassword(emaiId, passwd);
			
		}
		if (userObj == null) {
			throw new Exception("Mauvaise coordonnées");
		}
		
		return userObj;
		}
	
	
	
	

}

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
import com.example.demo.model.Secretaire;
import com.example.demo.model.Therapie;
import com.example.demo.repository.SecretaireRepository;
@RestController
@RequestMapping("/secretaire/")
public class SecretaireController {
	

	@Autowired
	private SecretaireRepository secretaireRepository;
	//get all secretaires
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAll")
	
	public List<Secretaire> getAllSecretaire(){
		return secretaireRepository.findAll();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createSecretaire")
	public Secretaire createSecretaire(@RequestBody Secretaire secretaire) {
		//System.out.println((secretaireRepository.save(secretaire)).getClass());
		return secretaireRepository.save(secretaire);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<Secretaire> getSecretaireById(@PathVariable Long id){
		Secretaire secretaire = secretaireRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Sécretaire non trouvé avec l'id :"+id));
		return ResponseEntity.ok(secretaire);
	}
	
	//update secretaire
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/update/{id}")
	public ResponseEntity<Secretaire> updateSecretaire(@PathVariable Long id,@RequestBody Secretaire secretaireInfo){
		Secretaire secretaire = secretaireRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Sécretaire non trouvé avec l'id :"+id));
		secretaire.setNom(secretaireInfo.getNom());
		secretaire.setAge(secretaireInfo.getAge());
		secretaire.setEmail(secretaireInfo.getEmail());
		secretaire.setPassword(secretaireInfo.getPassword());
		secretaire.setPrenom(secretaireInfo.getPrenom());
		secretaire.setTelephone(secretaireInfo.getTelephone());
		
		Secretaire secretaireAjour = secretaireRepository.save(secretaire);
		return ResponseEntity.ok(secretaireAjour);
	}
	
	

	
	//delete sécretaire
	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteMedecin(@PathVariable Long id){
		
		Secretaire secretaire = secretaireRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Sécretaire non trouvé avec l'id :"+id));
		secretaireRepository.delete(secretaire);
		//retourne void c'est pour cela qu'on a créer the map pour retourner une réponse
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/login")
	public Secretaire login(@RequestBody Secretaire secretaire ) throws Exception {
		String email = secretaire.getEmail();
		String password = secretaire.getPassword();
		Secretaire secretaireobj = null;
		if(email != null && password != null) {
			secretaireobj = secretaireRepository.findByEmailAndPassword(email, password);
			secretaireobj = secretaireRepository.getByEmailAndPassword(email, password);
			
			}	
			if(secretaireobj == null ) {
				throw new Exception("bad credentials");}
			
			
	return secretaireobj;}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/find/{email}")
	public ResponseEntity<Secretaire> findSecretaireByEmail(@PathVariable String email ){
		Secretaire secretaire = secretaireRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException ("Secretaire non trouvé avec l'email :"+email));
		return ResponseEntity.ok(secretaire);
	}
	


}

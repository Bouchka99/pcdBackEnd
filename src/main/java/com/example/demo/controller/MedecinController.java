package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Medecin;
import com.example.demo.model.Secretaire;

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

import com.example.demo.repository.MedecinRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/medecin/")
public class MedecinController {
	
	protected Medecin x ;//utilisé pour l'affichage du profil
	public Boolean newUser=false;
	@Autowired
	private MedecinRepository medecinRepository;
	//get all medecins
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAll")
	public List<Medecin> getAllMedecin(){
		return medecinRepository.findAll();
	}
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createMedecin")
	public Medecin createMedecin(@RequestBody Medecin medecin) {
		return medecinRepository.save(medecin);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id){
		Medecin medecin = medecinRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Médecin non trouvé avec l'id :"+id));
		return ResponseEntity.ok(medecin);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/find/{email}")
	public ResponseEntity<Medecin> findMedecinByEmail(@PathVariable String email ){
		Medecin medecin = medecinRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException ("Médecin non trouvé avec l'email :"+email));
		return ResponseEntity.ok(medecin);
	}
	//update médecin
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/update/{id}")
	public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id,@RequestBody Medecin medecinInfo){
		Medecin medecin = medecinRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));
		medecin.setNom(medecinInfo.getNom());
		medecin.setAge(medecinInfo.getAge());
		medecin.setEmail(medecinInfo.getEmail());
		medecin.setPassword(medecinInfo.getPassword());
		medecin.setPrenom(medecinInfo.getPrenom());
		medecin.setTelephone(medecinInfo.getTelephone());
		medecin.setSpecialite(medecinInfo.getSpecialite());
		
		Medecin medecinAjour = medecinRepository.save(medecin);
		return ResponseEntity.ok(medecinAjour);
	}
	
	//delete médecin
	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteMedecin(@PathVariable Long id){
		
		Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));
		medecinRepository.delete(medecin);
		//retourne void c'est pour cela qu'on a créer the map pour retourner une réponse
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/login")
	public Medecin login(@RequestBody Medecin medecin) throws Exception {
		
	//	long princ = medecin.getPrincipal();
		String emaiI = medecin.getEmail();
		String passw = medecin.getPassword();
		Medecin medecinobj = null;
		if(emaiI != null && passw != null) {
			medecinobj = medecinRepository.findByEmailAndPassword(emaiI, passw);
			medecinobj = medecinRepository.getByEmailAndPassword(emaiI, passw);
			
		}
		if (medecinobj == null) {
			throw new Exception("bad credentials");
		}
		x = medecinobj;
		
		System.out.println("hello");
		System.out.println(medecinobj);
		return medecinobj;
		}
	
	//profil
	/*
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/profil/{id}")
	public ResponseEntity<Map<String,Boolean>> findOne(@PathVariable Long id){
		
		Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));
		
		//retourne void c'est pour cela qu'on a créer the map pour retourner une réponse
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
	}
		*/
	
	
	/*@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/login/{id}")
	public ResponseEntity<Medecin> getMPById(){
		Medecin medecin = medecinRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Médecin non trouvé avec l'id :"+id));
		return ResponseEntity.ok(medecin);
	}*/

}

package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.model.Rendezvous;
import com.example.demo.repository.RendezvousRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/rendezvous/")
public class RendezvousController {

	
	 
	@Autowired
	private RendezvousRepository rendezvousrepository ;
	
	@Autowired
    private JavaMailSender javaMailSender;

	private List<Rendezvous> rendezvouss;
	
	@PostMapping("/createRendezvous")
	public Rendezvous createRendezvous(@RequestBody Rendezvous rendezvous) {
		
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("docotlib.ensi@gmail.com");
        msg.setTo(rendezvous.getEmail());
        msg.setSubject("Rendez-vous");
        msg.setText("Hello "+ rendezvous.getNom() + "Bienvenue dans notre application \n nous allons contacté dans .... ");
        javaMailSender.send(msg);
		return rendezvousrepository.save(rendezvous);
	}
	
	@GetMapping("/getAll")
	public List<Rendezvous> getAllRendezvous(){
		return rendezvousrepository.findAll();
	}
	
	@GetMapping("/getAllarrivee")
	public List<Rendezvous> getAllRendezvousarrivee(){
		
		rendezvouss = null;
		String arrivee = "en cours";
		/*
		Rendezvous findByArrivee = rendezvousrepository.findByArrivee(arrivee);
		System.out.println(findByArrivee.getArrivee());
		*/
		return rendezvousrepository.findByArrivee(arrivee);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Rendezvous> getRendezvousById(@PathVariable Long id){
		Rendezvous rendezvous = rendezvousrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("patient non trouvé avec l'id :"+id));
		return ResponseEntity.ok(rendezvous);
	}
	//update médecin
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/update/{id}")
	public ResponseEntity<Rendezvous> updateRendezvous(@PathVariable Long id,@RequestBody Rendezvous rendezvousInfo){
		Rendezvous rendezvous = rendezvousrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("patient non trouvé avec l'id :"+id));
		
		rendezvous.setNom(rendezvousInfo.getNom());
		rendezvous.setAge(rendezvousInfo.getAge());
		rendezvous.setDescription(rendezvousInfo.getDescription());
		rendezvous.setDisponibilite1(rendezvousInfo.getDisponibilite1());
		rendezvous.setDisponibilite2(rendezvousInfo.getDisponibilite2());
		rendezvous.setDisponibilite3(rendezvousInfo.getDisponibilite3());
		rendezvous.setTemps1(rendezvousInfo.getTemps1());
		rendezvous.setTemps2(rendezvousInfo.getTemps2());
		rendezvous.setTemps3(rendezvousInfo.getTemps3());
		rendezvous.setEmail(rendezvousInfo.getEmail());
		rendezvous.setPrenom(rendezvousInfo.getPrenom());
		rendezvous.setTelephone(rendezvousInfo.getTelephone());
		rendezvous.setArrivee(rendezvousInfo.getArrivee());
		
		
		
		Rendezvous rendezvousAjour = rendezvousrepository.save(rendezvous);
		return ResponseEntity.ok(rendezvousAjour);
	}
	/*public ResponseEntity<Rendezvous> updateRendezvous(@PathVariable Long id,@RequestBody Rendezvous rendezvousInfo){
	Rendezvous rendezvous = rendezvousrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("patient non trouvé avec l'id :"+id));
	
	rendezvous.setNom(rendezvous.getNom());
	rendezvous.setAge(rendezvous.getAge());
	rendezvous.setDescription(rendezvous.getDescription());
	rendezvous.setDisponibilite1(rendezvous.getDisponibilite1());
	rendezvous.setDisponibilite2(rendezvous.getDisponibilite2());
	rendezvous.setDisponibilite3(rendezvous.getDisponibilite3());
	rendezvous.setTemps1(rendezvous.getTemps1());
	rendezvous.setTemps2(rendezvous.getTemps2());
	rendezvous.setTemps3(rendezvous.getTemps3());
	rendezvous.setEmail(rendezvous.getEmail());
	rendezvous.setPrenom(rendezvous.getPrenom());
	rendezvous.setTelephone(rendezvous.getTelephone());
	rendezvous.setArrivee(rendezvousInfo.getArrivee());
	
	
	
	Rendezvous rendezvousAjour = rendezvousrepository.save(rendezvous);
	return ResponseEntity.ok(rendezvousAjour);
}*/
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteRendezvous(@PathVariable Long id){
		
		Rendezvous rendezvous = rendezvousrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("patient non trouvé avec l'id :"+id));
		rendezvousrepository.delete(rendezvous);
		//retourne void c'est pour cela qu'on a créer the map pour retourner une réponse
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
		
	}
}
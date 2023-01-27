package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/patient/")
public class PatientController {
	
	private Patient y;
	@Autowired
	private PatientRepository patientrepository;
	protected UserRepository userRepository;
	
	//get 
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAll")
	public List<Patient> getAllPatient(){
		return patientrepository.findAll();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/find/{email}")
	public ResponseEntity<Patient> findPatientByEmail(@PathVariable String email ){
		Patient patient = patientrepository.findByEmail(email);
		return ResponseEntity.ok(patient);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createPatient")
	public Patient createPatient(@RequestBody Patient patient) throws Exception {
		String emaiId = patient.getEmail();
		if(emaiId != null && !"".equals(emaiId)) {
			Patient	patientobj = patientrepository.findByEmail(emaiId);
		
			if (patientobj != null) {
					throw new Exception("bad credentials");
			}
		}
		/*User user = new User(patient.getEmail(),patient.getPassword(),patient.getRole());
		userRepository.save(user);
		System.out.println(user);*/
	
		return patientrepository.save(patient);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/update/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id,@RequestBody Patient patientInfo){
		Patient patient = patientrepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));
		patient.setNom(patientInfo.getNom());
		patient.setAge(patientInfo.getAge());
		patient.setEmail(patientInfo.getEmail());
		patient.setPassword(patientInfo.getPassword());
		patient.setPrenom(patientInfo.getPrenom());
		patient.setTelephone(patientInfo.getTelephone());

		
		Patient patientAjour = patientrepository.save(patient);
		return ResponseEntity.ok(patientAjour);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
		Patient patient = patientrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Médecin non trouvé avec l'id :"+id));
		return ResponseEntity.ok(patient);
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/login")
	public Patient login(@RequestBody Patient patient) throws Exception {

		String emaiId = patient.getEmail();
		String passwd = patient.getPassword();
		Patient patientobj = null;
		if(emaiId != null && passwd != null) {
			patientobj = patientrepository.findByEmailAndPassword(emaiId, passwd);
			patientobj = patientrepository.getByEmailAndPassword(emaiId, passwd);
			
		}
		if (patientobj == null) {
			throw new Exception("bad credentials");
		}
		y = patientobj;
		
		return patientobj;
		}
	
/*	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/registration")
	public Patient registration(@RequestBody Patient patient) throws Exception {

		String emaiId = patient.getEmail();
		Patient patientobj = null;
		if(emaiId != null && !"".equals(emaiId)) {
			patientobj = patientrepository.findByEmail(emaiId);
		}
		if (patientobj == null) {
			throw new Exception("bad credentials");
		}
		return patientobj;
		} */
}
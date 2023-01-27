package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Therapie;
import com.example.demo.repository.TherapieRepository;






import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/therapie/")
public class TherapieController {
	@Autowired
	private TherapieRepository therapieRepository;
	public int d;
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/upload")
	
	public Therapie uplaodImage(@RequestParam("imageFile") MultipartFile file ,
			@RequestParam("nomTherapie") String nomTherapie,@RequestParam("description") String description,
			@RequestParam("video") String video) throws IOException 
	{
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length );
		Therapie img1= new Therapie(nomTherapie,0, 0, 0, 0, 0, 0, 0, description,file.getOriginalFilename(), video);

		String folder ="/Users/Lenovo/Desktop/pcdNew/version1/src/assets/images/";
		byte [] bytes = file.getBytes();
		Path path = Paths.get(folder + file.getOriginalFilename());
		Files.write(path, bytes);
		return therapieRepository.save(img1);
		//return ResponseEntity.status(HttpStatus.OK);
	}

	
	//get all therapies

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAll")
	
	public List<Therapie> getAllTherapie(){
		return therapieRepository.findAll();
	}
	

	@PostMapping("/createTherapie")
	public Therapie createTherapie(@RequestBody Therapie therapie) {
		return therapieRepository.save(therapie);
	}
	
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Therapie> getTherapieById(@PathVariable Long id){
		Therapie therapie = therapieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Therapie non trouvé avec l'id :"+id));
		return ResponseEntity.ok(therapie);
	}
	
	

	@PutMapping("/update/{id}")
	public ResponseEntity<Therapie> updateTherapie(@PathVariable Long id,@RequestBody Therapie therapieInfo){
		Therapie therapie = therapieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Therapie non trouvé avec l'id :"+id));
		therapie.setNom(therapieInfo.getNom());
		therapie.setDescription(therapieInfo.getDescription());
		Therapie therapieAjour = therapieRepository.save(therapie);
		return ResponseEntity.ok(therapieAjour);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteMedecin(@PathVariable Long id){
		
		Therapie therapie = therapieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Thérapie non trouvé avec l'id :"+id));
		therapieRepository.delete(therapie);
		//retourne void c'est pour cela qu'on a créer the map pour retourner une réponse
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updateRate/{id}")
	public ResponseEntity<Therapie> updateTherapieRate(@PathVariable Long id,@RequestBody Therapie therapieInfo){
		Therapie therapie = therapieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Therapie non trouvé avec l'id :"+id));
		
		switch ((int)therapieInfo.getStarRating()) {
	      case 1:
	    	  therapie.setRateone(therapie.getRateone()+1);
	        break;
	      case 2:
	    	 therapie.setRatetwo(therapie.getRatetwo()+1);
	        break;
	      case 3:
	    	 therapie.setRatethree(therapie.getRatethree()+1);
	        break;
	      case 4:
	    	 therapie.setRatefour(therapie.getRatefour()+1);
	        break;
	      case 5:
	    	  therapie.setRatefive(therapie.getRatefive()+1);
	        break;
	    default: System.out.println("In next half");}
		this.d=(int) ((5*therapie.getRatefive() + 4*therapie.getRatefour() + 3*therapie.getRatethree()+ 2*therapie.getRatetwo() + 1*therapie.getRateone())/(therapie.getRatefive() + therapie.getRatefour() + therapie.getRatethree()+ therapie.getRatetwo()+therapie.getRateone()));
		therapie.setStarRating(this.d);
		therapie.setRateall(this.d);
		Therapie therapieAjour = therapieRepository.save(therapie);
		return ResponseEntity.ok(therapieAjour);
	}
	
	/*@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updateRate/{id}/")
	public ResponseEntity<Therapie> updateTherapieRate(@PathVariable Long id,PathVariable String email,@RequestBody Therapie therapieInfo){
		Therapie therapie = therapieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Therapie non trouvé avec l'id :"+id));
		int j = 0;
		for(int i = 0 ; i < therapie.getListeRate().size(); i++) {
			
			if(therapie.getListeRate().get(i)==email) {
				j=j+1;
			}
		}
		if (j==0) {
			therapie.getListeRate().add(email);
			switch ((int)therapieInfo.getStarRating()) {
		      case 1:
		    	  therapie.setRateone(therapie.getRateone()+1);
		        break;
		      case 2:
		    	 therapie.setRatetwo(therapie.getRatetwo()+1);
		        break;
		      case 3:
		    	 therapie.setRatethree(therapie.getRatethree()+1);
		        break;
		      case 4:
		    	 therapie.setRatefour(therapie.getRatefour()+1);
		        break;
		      case 5:
		    	  therapie.setRatefive(therapie.getRatefive()+1);
		        break;
		    default: System.out.println("In next half");}
			this.d=(int) ((5*therapie.getRatefive() + 4*therapie.getRatefour() + 3*therapie.getRatethree()+ 2*therapie.getRatetwo() + 1*therapie.getRateone())/(therapie.getRatefive() + therapie.getRatefour() + therapie.getRatethree()+ therapie.getRatetwo()+therapie.getRateone()));
			therapie.setStarRating(this.d);
			therapie.setRateall(this.d);

		}
		Therapie therapieAjour = therapieRepository.save(therapie);
		return ResponseEntity.ok(therapieAjour);

	}*/
	
	//
	/*@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updatelistRate/{id}/")
	public ResponseEntity<Therapie> updateTherapielistRate(@PathVariable Long id,@RequestBody String email){
		Therapie therapie = therapieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException ("Therapie non trouvé avec l'id :"+id));
		int j = 0;
		for(int i = 0 ; i < therapie.getListeRate().size(); i++) {
			
			if(therapie.getListeRate().get(i)==email) {
				j=j+1;
			}
		}
		if (j==0) {
			therapie.getListeRate().add(email);
		}
		Therapie therapieAjour = therapieRepository.save(therapie);
		return ResponseEntity.ok(therapieAjour);
	}*/



	
	
}
	
	
	

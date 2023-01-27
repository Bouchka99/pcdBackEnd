package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Medecin;
import com.example.demo.model.Patient;

//import com.example.cabinetbackend.model.Patient;



@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	//public Optional<Medecin> findByEmail(String email);
	public Patient findByEmailAndPassword(String email , String password);
	public Patient findByEmail(String email );
	public Patient getByEmailAndPassword(String email , String password);
}
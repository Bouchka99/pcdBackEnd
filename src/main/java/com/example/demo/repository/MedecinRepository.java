package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Medecin;


@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long>{
	
	public Medecin findByEmailAndPassword(String email , String password );
	public Medecin getByEmailAndPassword(String email , String password );
	public Optional<Medecin> findByEmail(String email);

}
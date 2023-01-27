package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Medecin;
import com.example.demo.model.Secretaire;

@Repository
public interface SecretaireRepository extends JpaRepository<Secretaire, Long>{
	
	public Secretaire findByEmailAndPassword(String email , String password);
	public Secretaire getByEmailAndPassword(String email , String password);
	public Optional<Secretaire> findByEmail(String email);
}
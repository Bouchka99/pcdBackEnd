package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Therapie;
@Repository
public interface TherapieRepository extends JpaRepository<Therapie, Long>{
	
}

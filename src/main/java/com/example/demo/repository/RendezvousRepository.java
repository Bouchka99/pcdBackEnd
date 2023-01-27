package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Rendezvous;

@Repository
public interface RendezvousRepository  extends JpaRepository<Rendezvous , Long> {

public List<Rendezvous> findByArrivee(String arrivee);

}
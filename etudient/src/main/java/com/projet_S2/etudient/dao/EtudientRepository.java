package com.projet_S2.etudient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet_S2.etudient.model.Etudient;
@Repository
public interface EtudientRepository extends JpaRepository<Etudient,Integer> {
	
	Etudient findByCin(String cin);
	Etudient findById(int id);
	void deleteById(int id);
	
}

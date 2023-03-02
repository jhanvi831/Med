package com.medical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.medical.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	public Patient findById(int id);
}

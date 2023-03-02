package com.medical.services;

import com.medical.dao.PatientRepository;
import com.medical.entities.Patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PatientServices { //DAO Layer
	public List<Patient> getAllPatients();
	public Patient getById(int id);
	public Patient addPatient(Patient p);
	public void deletePatient(int bid);
	public void updatePatient(Patient patient, int patientId);
}
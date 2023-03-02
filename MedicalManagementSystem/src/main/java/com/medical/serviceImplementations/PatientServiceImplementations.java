package com.medical.serviceImplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medical.dao.PatientRepository;
import com.medical.entities.Patient;
import com.medical.services.PatientServices;

@Component
public class PatientServiceImplementations implements PatientServices  {
	
	@Autowired
	PatientRepository patientRepository;
	
	//get all books
	@Override
	public List<Patient> getAllPatients(){
		List<Patient> list =this.patientRepository.findAll();
		return list;
	}
	
	//get single book by id
	@Override
	public Patient getById(int id) {
		Patient patient=null;
		try {
			patient = this.patientRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}
	
	//adding the book
	@Override
	public Patient addPatient(Patient p) {
		Patient result = patientRepository.save(p);
		return result;
	}
	
	//delete a book
	@Override
	public void deletePatient(int bid) {
		
		patientRepository.deleteById(bid);
	}
	
	//update patient details
	@Override
	public void updatePatient(Patient patient, int patientId) {
		
		patient.setId(patientId);
		patientRepository.save(patient);
		
	}
}

package com.medical.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.medical.entities.Patient;
import com.medical.services.PatientServices;

@RestController
public class PatientController{

	@Autowired
	private PatientServices patientService;

		
	//Get All existing Patients list 
	@GetMapping(value="/patient")
	public ResponseEntity< List<Patient> > getPatients() {
		List<Patient> list = this.patientService.getAllPatients();		
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			//Error Handling
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}
		
		
		
	//return single patient
	@GetMapping(value="patient/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable("id") int id){
		Patient patient = patientService.getById(id);
		if(patient==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(patient));
	}
		
		
		
	//adding a new patient
	@PostMapping(value="/patient")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
		Patient p = null; 	
		try {
		 		p = this.patientService.addPatient(patient);
		 		System.out.println(patient);
		 		return ResponseEntity.of(Optional.of(p));
			}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
		
		
		
	//deleting an existing patient
	@DeleteMapping(value="/patient/{patientId}")
	public ResponseEntity<Void> deletedBook(@PathVariable("patientId") int patientId) {
		try {
			this.patientService.deletePatient(patientId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
	}
		
		
		
	//updating an existing patient's details
	@PutMapping(value="patient/{patientId}")
	public ResponseEntity<Patient> updateBook(@RequestBody Patient patient, @PathVariable("patientId") int patientId) {
		try {
			this.patientService.updatePatient(patient, patientId);
			return ResponseEntity.ok().body(patient);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

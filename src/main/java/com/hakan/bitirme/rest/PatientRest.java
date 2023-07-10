package com.hakan.bitirme.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakan.bitirme.domain.Patient;
import com.hakan.bitirme.service.PatientService;

@RestController
@RequestMapping("/rest/patient")
public class PatientRest{

	
	@Autowired
	private PatientService patientService;
	
	
	//Bütün Kullaniciları Getirir
		@GetMapping("/getPatients")
		private List<Patient> getPatientsList() {
			
			return patientService.getPatientList();
		}
	
		
		//İD ye göre kullanici getirir
		@GetMapping("/getPatientById/{patientId}")
		private Patient patientById(
				@PathVariable(name = "patientId", required = true) Long patientId) {
			return patientService.selectedPatient(patientId);
		}
		
		//Yeni kullanici kaydeder
		@PostMapping("/postSavePatient")
		public Patient savePatient(@RequestBody Patient patient) {
			return patientService.savePatient(patient);
			
		}
}

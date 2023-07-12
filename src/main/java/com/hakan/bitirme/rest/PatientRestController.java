package com.hakan.bitirme.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakan.bitirme.domain.Patient;
import com.hakan.bitirme.service.PatientService;
import com.hamza.bitirme.model.Kullanici;

@RestController
@RequestMapping("/rest/patient")
public class PatientRestController {

	@Autowired
	private PatientService patientService;

	// Bütün hastaları getirir Getirir
	@GetMapping("/getPatients")
	private List<Patient> getPatientsList() {

		return patientService.getPatientList();
	}

	// id ye göre hasta getirir
	@GetMapping("/getPatientById/{patientId}")
	private Patient patientById(@PathVariable(name = "patientId", required = true) Long patientId) {
		return patientService.selectedPatient(patientId);
	}

	// yeni hasta kaydeder
	@PostMapping("/postSavePatient")
		public Patient savePatient(@RequestBody Patient patient) {
			return patientService.savePatient(patient);
			
	}
	
	
			//HASTA Bilgilerini günceller
	@PutMapping("/putUpdatePatientById/{patientId}")
	public Patient updatePatient(@RequestBody Patient patient,
			@PathVariable(name = "patienId", required = true) Long patientId) {

		Patient savedPatient = patientService.selectedPatient(patientId);

		savedPatient.setFirstName(patient.getFirstName());
		savedPatient.setLastName(patient.getLastName());
		savedPatient.setEmail(patient.getEmail());
		savedPatient.setPassword(patient.getPassword());

		return patientService.savePatient(savePatient(savedPatient));
	}

	
	

}




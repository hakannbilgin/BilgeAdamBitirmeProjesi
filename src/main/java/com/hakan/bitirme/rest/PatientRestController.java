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
import com.hakan.bitirme.dto.patientdto.PatientDTO;
import com.hakan.bitirme.service.PatientService;

@RestController
@RequestMapping("/rest/patient")
public class PatientRestController {
	@Autowired
	private PatientService patientService;

//	// yeni hasta kaydeder
//	@PostMapping("/postSavePatient")
//	public Patient savePatient(@RequestBody Patient patient) {
//		return patientService.savePatient(patient);
//
//	}

	// id ye göre hasta getirir
	@GetMapping("/getPatientById/{patientId}")
	private Patient patientById(@PathVariable(name = "patientId", required = true) Long patientId) {
		return patientService.selectedPatient(patientId);
	}

	// HASTA Bilgilerini günceller
	@PutMapping("/putUpdatePatientById/{patientId}")
	public Patient updatePatient(@RequestBody Patient patient,
			@PathVariable(name = "patientId", required = true) Long patientId) {
		Patient savedPatient = patientService.selectedPatient(patientId);

		savedPatient.setFirstName(patient.getFirstName());
		savedPatient.setLastName(patient.getLastName());
		savedPatient.setEmail(patient.getEmail());
		savedPatient.setPassword(patient.getPassword());

		return patientService.savePatient(savedPatient);
	}

	// Bütün hastaları getirir Getirir
//	@GetMapping("/getPatients")
//	private List<Patient> getPatientsList() {
//
//		return patientService.getPatientList();
//	}

	
	// İd ye göre hastaları siler
		@DeleteMapping("/deletePatientById/{patientId}")
		public Boolean deletePatient(@PathVariable(name = "patientId", required = true) Long patientId) {

			return patientService.deletePatient(patientId) ? true : false;

		}
		
		
	// Bütün hastaları siler
	@RequestMapping("/deleteAllPatients")
	public Boolean deleteAllPatients() {
		return patientService.deleteAllPatients() ? true : false;
	}

	// id ye göre hasta getirir
		@GetMapping("/getPatientByCitizenNumber/{citizenNumber}")
		private Patient getPatientByCitizenNumber(@PathVariable(name = "citizenNumber", required = true) String CitizenNumber) {
			return patientService.getPatientByCitizenNumber(CitizenNumber);
		}
		
		// yeni hasta kaydeder
		@PostMapping("/postSavePatientDTO")
		public Patient savePatientWith(@RequestBody PatientDTO dto) {
			return patientService.savePatientWithDTO(dto);

		}
		
		@GetMapping("/getPatientsDTO")
		private List<PatientDTO> getPatientsListWith() {

			return patientService.getPatientListWithDTO();
		}
		
		
//		@PutMapping("/putUpdatePatientById/{patientId}")
//		public Patient updatePatient(@RequestBody PatientDTO patientDTO,
//				@PathVariable(name = "patientId", required = true) Long patientId) {
//			
//			Patient savedPatient = patientService.selectedPatient(patientId);
//
//			savedPatient.setFirstName(patientDTO.getFirstName());
//			savedPatient.setLastName(patientDTO.getLastName());
//			savedPatient.setEmail(patientDTO.getEmail());
//			savedPatient.setPassword(patientDTO.getPassword());
//
//			return patientService.savePatient(savedPatient);
//		}
		
		
		
}

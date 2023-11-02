package com.hakan.bitirme.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.hakan.bitirme.dto.patientdto.PatientMapper;
import com.hakan.bitirme.service.PatientService;

@RestController
@RequestMapping("/rest/patient")
public class PatientRestController {
	@Autowired
	private PatientService patientService;

	private PatientMapper patientMapper;

	@Autowired
	public PatientRestController(PatientMapper patientMapper) {
		this.patientMapper = patientMapper;
	}

//	// İd ye göre hastaları siler
//	@DeleteMapping("/deletePatientById/{patientId}")
//	public Boolean deletePatient(@PathVariable(name = "patientId", required = true) Long patientId) {
//
//		return patientService.deletePatient(patientId) ? true : false;
//
//	}
//
//	// Bütün hastaları siler
//	@RequestMapping("/deleteAllPatients")
//	public Boolean deleteAllPatients() {
//		return patientService.deleteAllPatients() ? true : false;
//	}

	// id ye göre hasta getirir
//	@GetMapping("/getPatientByCitizenNumber/{citizenNumber}")
//	private Patient getPatientByCitizenNumber(
//			@PathVariable(name = "citizenNumber", required = true) String CitizenNumber) {
//		return patientService.getPatientByCitizenNumber(CitizenNumber);
//	}

//	// yeni hasta kaydeder
//	@PostMapping("/postSavePatientDTO")
//	public Patient savePatientDTO(@RequestBody PatientDTO dto) {
//		return patientService.savePatientWithDTO(dto);
//
//	}

//	@GetMapping("/getPatientByIdDTO/{patientId}")
//	private PatientDTO patientByIdDTO(@PathVariable(name = "patientId", required = true) Long patientId) {
//
//		return patientService.selectedPatientWithDTO(patientId);
//	}
//
//	@GetMapping("/getPatientsDTO")
//	private List<PatientDTO> getPatientsListDTO() {
//
//		return patientService.getPatientListWithDTO();
//	}

//	@PutMapping("/putUpdatePatientByIdDTO/{patientId}")
//	public Patient updatePatientDTO(@RequestBody PatientDTO patientDTO,
//			@PathVariable(name = "patientId", required = true) Long patientId) {
//
//		PatientDTO savedPatient = patientService.selectedPatientWithDTO(patientId);
//
//		savedPatient.setFirstName(patientDTO.getFirstName());
//		savedPatient.setLastName(patientDTO.getLastName());
//		savedPatient.setEmail(patientDTO.getEmail());
//		savedPatient.setPassword(patientDTO.getPassword());
//
//		return patientService.savePatientWithDTO(savedPatient);
//	}

	@GetMapping("/getPatientPasswordByCitizenNumberDTO/{citizenNumber}")
	private String getPatientPasswordByCitizenNumberDTO(
			@PathVariable(name = "citizenNumber", required = true) String CitizenNumber) {

		return patientService.getSelectedPatientPasswordWithDTO(CitizenNumber);
	}

	@GetMapping("/patientRegisterCheck/{citizenNumber}")
	private Boolean patientRegisterCheckDTO(
			@PathVariable(name = "citizenNumber", required = true) String CitizenNumber) {
		return patientService.patientRegisterCheckWithDTO(CitizenNumber);
	}

//	@GetMapping("/getPatientByCitizenNumberDTO/{citizenNumber}")
//	private PatientDTO getpatientByCitizenNumberDTO(
//			@PathVariable(name = "citizenNumber", required = true) String citizenNumber) {
//
//		return patientService.getPatientByCitizenNumberWithDTO(citizenNumber);
//	}
//	
//	
//	
//	
//	
//	
//	
//	

	@PostMapping("/savePatientResponse")
	public ResponseEntity<String> savePatientResponse(@RequestBody PatientDTO patientDTO) {
//BURAYI DÜZENLE
		try {
			patientService.savePatientWithDTO(patientDTO);
			return ResponseEntity.ok("Patient saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't saved");
		}
	}

	@GetMapping("/getPatientByIdResponse/{patientId}")
	private ResponseEntity<?> getpatientByIdResponse(
			@PathVariable(name = "patientId", required = true) Long patientId) {

		try {
			if (patientService.selectedPatientWithDTO(patientId) != null) {
				PatientDTO patientDTO = patientService.selectedPatientWithDTO(patientId);
				return ResponseEntity.ok(patientDTO);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving the patient.");
		}
	}

	@GetMapping("/getPatientByCitizenNumber/{citizenNumber}")
	private ResponseEntity<?> getPatientByCitizenNumberResponse(
			@PathVariable(name = "citizenNumber", required = true) String CitizenNumber) {

		try {
			if (patientService.getPatientByCitizenNumberwithDTO(CitizenNumber) != null) {
				PatientDTO patientDTO = patientService.getPatientByCitizenNumberwithDTO(CitizenNumber);
				return ResponseEntity.ok(patientDTO);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving the patient.");
		}

	}

	@DeleteMapping("/deletePatientByIdResponse/{patientId}")
	public ResponseEntity<String> deletePatientByIdResponse(@PathVariable(name = "patientId") Long patientId) {
//		boolean isDeleted = patientService.deletePatient(patientId);
//		if (isDeleted) {
//			return ResponseEntity.ok("Patient with ID " + patientId + " has been deleted.");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + patientId + " not found.");
//		}
//		Boolean isDeleted2 = patientService.deletePatient(patientId);
//        System.out.println(isDeleted2);

		try {

			if (patientService.selectedPatientWithDTO(patientId) != null) {
				patientService.deletePatient(patientId);
				return ResponseEntity.ok("Patient with ID " + patientId + " has been deleted.");
			} else {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + patientId + " not found.");
			}
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting the patient.");

		}

	}

	@DeleteMapping("/deleteAllPatientsResponse")
	public ResponseEntity<String> deleteAllPatientsResponse() {
//		boolean isDeleted = patientService.deleteAllPatients();
//		if (isDeleted) {
//			return ResponseEntity.ok("All patients have been deleted.");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patients found to delete.");
//		}
//	}

		try {
			boolean isListEmpty = patientService.getPatientListWithDTO().isEmpty();
			if (!isListEmpty) {
				patientService.deleteAllPatients();
				return ResponseEntity.ok("All patients have been deleted.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patients found to delete.");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting the patient.");
		}

	}

	@GetMapping("/getPatientsListResponse")
	public ResponseEntity<?> getPatientsListResponse() {

//		List<PatientDTO> patientsList = patientService.getPatientListWithDTO();
//		if (patientsList != null && !patientsList.isEmpty()) {
//			return ResponseEntity.ok(patientsList);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//		

		try {
			List<PatientDTO> patientsList = patientService.getPatientListWithDTO();
			if (patientsList != null && !patientsList.isEmpty()) {
				return ResponseEntity.ok(patientsList);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no patient to List");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while Listing the patients.");
		}

	}

	@PutMapping("/putUpdatePatientByIdResponse/{patientId}")
	public ResponseEntity<?> updatePatientResponse(@RequestBody PatientDTO patientDTO,
			@PathVariable(name = "patientId", required = true) Long patientId) {

//		PatientDTO savedPatient = patientService.selectedPatientWithDTO(patientId);
//
//		if ((savedPatient == null)) {
//			return ResponseEntity.notFound().build();
//		}
//
//		savedPatient.setFirstName(patientDTO.getFirstName());
//		savedPatient.setLastName(patientDTO.getLastName());
//		savedPatient.setEmail(patientDTO.getEmail());
//		savedPatient.setPassword(patientDTO.getPassword());
//
//		PatientDTO updatedPatient = patientMapper.toDTO(patientService.savePatientWithDTO(savedPatient));
//
//		if (updatedPatient != null) {
//			return ResponseEntity.ok(updatedPatient);
//		} else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}

		try {
			PatientDTO savedPatient = patientService.selectedPatientWithDTO(patientId);

			if ((savedPatient == null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + patientId + " not found.");
			}

			savedPatient.setFirstName(patientDTO.getFirstName());
			savedPatient.setLastName(patientDTO.getLastName());
			savedPatient.setEmail(patientDTO.getEmail());
			savedPatient.setPassword(patientDTO.getPassword());

			PatientDTO updatedPatient = patientMapper.toDTO(patientService.savePatientWithDTO(savedPatient));

			if (updatedPatient != null) {
				return ResponseEntity.ok(updatedPatient);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + patientId + " not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating the patients.");

		}

	}

}

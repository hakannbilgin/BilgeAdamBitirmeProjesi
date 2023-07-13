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

	// HASTA Bilgilerini günceller
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

	// İd ye göre hastaları siler
	@DeleteMapping("/deletePatientById/{patientId}")
	public Boolean deletePatient(@PathVariable(name = "patientId", required = true) Long patientId) {

		return patientService.deletePatient(patientId) ? true : false;

	}

	// Bütün hastaları siler
	@RequestMapping("/deletePatient")
	public Boolean allKullaniciSil() {
		return patientService.deleteAllPatients() ? true : false;
	}

	// email ile kullanıcı çağrısı yapar
	@RequestMapping("/withCitizenNumber")
	public Patient showForm2(@RequestBody Patient patient) {
		System.out.println("kimlik no  geldi" + patient.getCitizenNumber());
		System.out.println("sifre : " + patientService.checkPatientRegister((patient.getCitizenNumber())));
		return patientService.registeredPatientByCitizenNumber(patient.getCitizenNumber());
	}

	// email ile kullanıcı sifre çağrısı yapar
	@RequestMapping("/emailIleSifre")
	public Patient showForm3(@RequestBody Patient patient) {

		return patientService.registeredPatientByCitizenNumber(patient.getCitizenNumber());
	}

}

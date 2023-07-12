package com.hakan.bitirme.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hakan.bitirme.domain.LogInPatient;
import com.hakan.bitirme.domain.Patient;
import com.hakan.bitirme.service.PatientService;
import lombok.Data;

@Controller("patientController")
@Data
@Scope("request")
public class PatientController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Patient> patientList;

	private String loginPassword;

	private Patient patient;

	private LogInPatient logInPatient;

	private Patient selectedPatient;

	@Autowired
	private PatientService patientService;

	@javax.annotation.PostConstruct
	public void init() {
		setPatient(new Patient());

		setPatientList(patientService.getPatientList());
	}

	public void newPatient() {
		this.patient = new Patient();
	}

	public void showSelectedPatient() {
		setPatient(getSelectedPatient());

	}

	public void savePatient() {

		if (patientService.patientRegisterControl(patient.getCitizenNumber()) == false) {

			try {
				this.patientService.savePatient(this.patient);
				setPatientList(patientService.getPatientList());

			} catch (Exception e) {

			}
		} else {

		}

	}

	public void patienLogIn() {

		try {

			if (patientService.getSelectedPatientPassword(patient.getCitizenNumber()).equals(patient.getPassword())) {

			} else {

			}
		} catch (Exception e) {

		}
	}

}

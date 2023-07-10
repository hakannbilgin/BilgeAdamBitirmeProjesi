package com.hakan.bitirme.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hakan.bitirme.domain.Patient;
import com.hakan.bitirme.service.PatientService;

import lombok.Data;


@Controller("patientController")
@Data
@Scope("request")
public class PatientController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Patient patient;

	private List<Patient> patientList;
	@Autowired
	private PatientService patientService;
	
	@javax.annotation.PostConstruct
	public void init() {
		setPatient(new Patient());
	
		setPatientList(patientService.getPatientList());
	}
	

	public void savePatient() {
		
		if(patientService.patientRegisterControl(patient.getCitizenNumber()) == false) {
		
			try {
				this.patientService.savePatient(this.patient);
				setPatientList(patientService.getPatientList());
			
			
			} catch (Exception e) {
				

			}
		}
		else {
		
		}

	}
}

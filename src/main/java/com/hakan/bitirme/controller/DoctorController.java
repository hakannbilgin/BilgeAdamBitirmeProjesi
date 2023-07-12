package com.hakan.bitirme.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.service.DoctorService;
import lombok.Data;

@Controller("doctorController")
@Data
public class DoctorController implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Doctor doctor;
	
	private List<Doctor> doctorList;
	@Autowired
	private DoctorService doctorService;
	
	
	
	
	@javax.annotation.PostConstruct
	public void init() {
		setDoctor(new Doctor());
	
		setDoctorList(doctorService.getDoctorsList());
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

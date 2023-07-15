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

import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.service.DoctorService;

@RestController
@RequestMapping("/rest/doctor")
public class DoctorRestController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/saveDoctor")
	public Doctor saveDoctor(@RequestBody Doctor doctor) {

		return doctorService.saveDoctor(doctor);
	}
	
	
	// doktorları Listeler
		@GetMapping("/getDoctors")
		public List<Doctor> getDoctorList() {

			return doctorService.getDoctorsList();
		}
		
		//seçilen doktoru getirir
		@GetMapping("/getDoctorById/{doctorId}")
		public Doctor getDoctorById(@PathVariable(name = "doctorId", required = true) Long doctorId) {

			return doctorService.selectedDoctor(doctorId);

		}
		
		//seçilen doktoru getirir
				@GetMapping("/getDoctorByBranch/{branch}")
				public List<Doctor> getDoctor(@PathVariable(name = "branch", required = true) String branch) {

					return doctorService.getDoctorsListByBranch(branch);

				}
		
		@PutMapping("/updateDoctorBranch/{doctorId}")
		public Doctor updateDoctorBranch(@RequestBody Doctor doctor,
				@PathVariable(name = "doctorId", required = true) Long doctorId) {

			Doctor savedDoctor = doctorService.selectedDoctor(doctorId);

			savedDoctor.setBranch(doctor.getBranch());

			return doctorService.saveDoctor(savedDoctor);
		}
		
		@DeleteMapping("/deleteDoctorById/{doctorId}")
		public Boolean deleteDoctorById(@PathVariable(required = true) Long doctorId) {

			return doctorService.deleteDoctorByID(doctorId) ? true : false;

		}
		
}

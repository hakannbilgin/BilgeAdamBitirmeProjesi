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

	// doktorları Listeler
	@GetMapping("/list")
	public List<Doctor> urunListesiniVer() {

		return doctorService.getDoctorsList();
	}

	//seçilen doktoru getirir
	@GetMapping("/select/{doctorId}")
	public Doctor getDoctor(@PathVariable(name = "doctorId", required = true) Long doctorId) {
		System.out.println("aaa" + doctorId);
		System.out.println("bbb" + doctorService.selectedDoctor(doctorId));
		return doctorService.selectedDoctor(doctorId);

	}
	
	@PostMapping("/save")
	public Doctor saveDoctor(@RequestBody Doctor doctor) {

		return doctorService.saveDoctor(doctor);
	}

	@PutMapping("/update/{doctorId}")
	public Doctor updateDoctor(@RequestBody Doctor doctor,
			@PathVariable(name = "doctorId", required = true) Long doctorId) {

		Doctor savedDoctor = doctorService.selectedDoctor(doctorId);

//		savedDoctor.setUrunaciklama(urun.getUrunaciklama());
//		savedDoctor.setUrunadi(urun.getUrunadi());
		savedDoctor.setBranch(doctor.getBranch());

		return doctorService.saveDoctor(savedDoctor);
	}

	@DeleteMapping("/delete/{doctorId}")
	public Boolean deleteDoctor(@PathVariable(required = true) Long doctorId) {

		return doctorService.deleteDoctor(doctorId) ? true : false;

	}

}

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
import com.hakan.bitirme.dto.doctordto.DoctorDTO;
import com.hakan.bitirme.service.DoctorService;

@RestController
@RequestMapping("/rest/doctor")
public class DoctorRestController {

	@Autowired
	private DoctorService doctorService;

	@DeleteMapping("/deleteDoctorById/{doctorId}")
	public Boolean deleteDoctorById(@PathVariable(required = true) Long doctorId) {

		return doctorService.deleteDoctorByID(doctorId) ? true : false;

	}

	@PostMapping("/saveDoctorDTO")
	public Doctor saveDoctorWith(@RequestBody DoctorDTO doctorDTO) {

		return doctorService.saveDoctorWithDTO(doctorDTO);
	}

	@GetMapping("/getDoctorByIdWith/{doctorId}")
	public DoctorDTO getDoctorByIdWith(@PathVariable(name = "doctorId", required = true) Long doctorId) {

		return doctorService.selectedDoctorWithDTO(doctorId);

	}

	@GetMapping("/getDoctorsDTO")
	public List<DoctorDTO> getDoctorListWith() {

		return doctorService.getDoctorsListWithDTO();
	}

	@GetMapping("/getDoctorsByBranchDTO/{branch}")
	public List<DoctorDTO> getDoctorListByBranchWith(@PathVariable(name = "branch", required = true) String branch) {

		return doctorService.getDoctorsListByBranchWithDTO(branch);
	}

	@PutMapping("/updateDoctorBranchDTO/{doctorId}")
	public Doctor updateDoctorBranchWith(@RequestBody DoctorDTO doctorDTO,
			@PathVariable(name = "doctorId", required = true) Long doctorId) {

		DoctorDTO savedDoctor = doctorService.selectedDoctorWithDTO(doctorId);

		savedDoctor.setBranch(doctorDTO.getBranch());

		return doctorService.saveDoctorWithDTO(savedDoctor);
	}

}

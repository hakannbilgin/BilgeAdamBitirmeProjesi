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

import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.dto.doctordto.DoctorDTO;
import com.hakan.bitirme.dto.doctordto.DoctorMapper;
import com.hakan.bitirme.dto.patientdto.PatientDTO;
import com.hakan.bitirme.service.DoctorService;

@RestController
@RequestMapping("/rest/doctor")
public class DoctorRestController {

	@Autowired
	private DoctorService doctorService;
	
	private DoctorMapper doctorMapper;
	
	public DoctorRestController(DoctorMapper doctorMapper) {
		this.doctorMapper= doctorMapper;
	}

	@DeleteMapping("/deleteDoctorById/{doctorId}")
	public Boolean deleteDoctorById(@PathVariable(required = true) Long doctorId) {

		return doctorService.deleteDoctorByID(doctorId) ? true : false;

	}

//	@PostMapping("/saveDoctorDTO")
//	public Doctor saveDoctorWith(@RequestBody DoctorDTO doctorDTO) {
//
//		return doctorService.saveDoctorWithDTO(doctorDTO);
//	}

//	@GetMapping("/getDoctorByIdWith/{doctorId}")
//	public DoctorDTO getDoctorByIdWith(@PathVariable(name = "doctorId", required = true) Long doctorId) {
//
//		return doctorService.selectedDoctorWithDTO(doctorId);
//
//	}

//	@GetMapping("/getDoctorsDTO")
//	public List<DoctorDTO> getDoctorListWith() {
//
//		return doctorService.getDoctorsListWithDTO();
//	}

//	@GetMapping("/getDoctorsByBranchDTO/{branch}")
//	public List<DoctorDTO> getDoctorListByBranchWith(@PathVariable(name = "branch", required = true) String branch) {
//
//		return doctorService.getDoctorsListByBranchWithDTO(branch);
//	}

//	@PutMapping("/updateDoctorBranchDTO/{doctorId}")
//	public Doctor updateDoctorBranchWith(@RequestBody DoctorDTO doctorDTO,
//			@PathVariable(name = "doctorId", required = true) Long doctorId) {
//
//		DoctorDTO savedDoctor = doctorService.selectedDoctorWithDTO(doctorId);
//
//		savedDoctor.setBranch(doctorDTO.getBranch());
//
//		return doctorService.saveDoctorWithDTO(savedDoctor);
//	}

//	
//	
//	
//	
//	
//	
//	
//	
//	

	@PostMapping("/saveDoctorResponse")
	public ResponseEntity<?> saveDoctorResponse(@RequestBody DoctorDTO doctorDTO) {

		try {

			if (doctorDTO != null) {
				doctorService.saveDoctorWithDTO(doctorDTO);
				return ResponseEntity.ok(doctorDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DoctorDTO is required");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't saved");
		}
	}

	@GetMapping("/getDoctorByIdResponse/{doctorId}")
	private ResponseEntity<?> getDoctorByIdResponse(@PathVariable(name = "doctorId", required = true) Long doctorId) {

		try {
			if (doctorService.selectedDoctorWithDTO(doctorId) != null) {
				DoctorDTO doctorDTO = doctorService.selectedDoctorWithDTO(doctorId);
				return ResponseEntity.ok(doctorDTO);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving the doctor.");
		}
	}

	@GetMapping("/getDoctorsListResponse")
	public ResponseEntity<?> getDoctorsListResponse() {

		try {
			List<DoctorDTO> doctorsList = doctorService.getDoctorsListWithDTO();
			if (doctorsList != null && !doctorsList.isEmpty()) {
				return ResponseEntity.ok(doctorsList);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no doctor to List");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while Listing the doctors.");
		}

	}

	@GetMapping("/getDoctorsListByBranchResponse/{branch}")
	public ResponseEntity<?> getDoctorsListByBranchResponse(
			@PathVariable(name = "branch", required = true) String branch) {

		try {

			if (branch != null) {
				List<DoctorDTO> doctorsList = doctorService.getDoctorsListByBranchWithDTO(branch);
				if (doctorsList != null && !doctorsList.isEmpty()) {
					return ResponseEntity.ok(doctorsList);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no doctor to List");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no doctor to List");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while Listing the doctors.");
		}

	}
	
	@PutMapping("/putUpdateDoctorByIdResponse/{doctorId}")
	public ResponseEntity<?> updateDoctorResponse(@RequestBody DoctorDTO doctorDTO,
			@PathVariable(name = "doctorId", required = true) Long doctorId) {


		try {
			DoctorDTO savedDoctor = doctorService.selectedDoctorWithDTO(doctorId);

			if ((savedDoctor == null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor with ID " + doctorId + " not found.");
			}

			savedDoctor.setBranch(doctorDTO.getBranch());

			DoctorDTO updatedDoctor = doctorMapper.toDTO(doctorService.saveDoctorWithDTO(savedDoctor));

			if (updatedDoctor != null) {
				return ResponseEntity.ok(updatedDoctor);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor with ID " + doctorId + " not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating the Doctor.");

		}

	}
	
	
	@DeleteMapping("/deleteDoctorByIdResponse/{doctorId}")
	public ResponseEntity<String> deleteDoctorByIdResponse(@PathVariable(name = "doctorId") Long doctorId) {

		try {

			if (doctorService.selectedDoctorWithDTO(doctorId) != null) {
				doctorService.deleteDoctorByID(doctorId);
				return ResponseEntity.ok("Doctor with ID " + doctorId + " has been deleted.");
			} else {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor with ID " + doctorId + " not found.");
			}
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting the doctor.");

		}

	}
	
	

}

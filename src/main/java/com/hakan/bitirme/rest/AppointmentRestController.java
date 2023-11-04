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

import com.hakan.bitirme.domain.Appointment;
import com.hakan.bitirme.dto.appointmentdto.AppointmentDTO;
import com.hakan.bitirme.dto.appointmentdto.AppointmentMapper;
import com.hakan.bitirme.service.AppointmentService;

@RestController
@RequestMapping("/rest/appointment")
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;

	private AppointmentMapper appointmentMapper;

	public AppointmentRestController(AppointmentMapper appointmentMapper) {
		this.appointmentMapper = appointmentMapper;

	}

//	@DeleteMapping("/deleteAppointment/{appointmentId}")
//	public Boolean deleteAppointment(@PathVariable(name = "appointmentId", required = true) Long AppointmentId) {
//
//		return appointmentService.deleteAppointmentbyId(AppointmentId) ? true : false;
//
//	}

//	@PostMapping("/saveAppointmentDTO")
//	public Appointment saveAppointmentWith(@RequestBody AppointmentDTO appointmentDTO) {
//
//		return appointmentService.saveAppointmentWithDTO(appointmentDTO);
//	}

//	@PostMapping("/saveAppointmentDTO1")
//	public Appointment saveAppointmentWith1(@RequestBody AppointmentDTO appointmentDTO) {
//		System.out.println("AppointmentDTORestController....." + appointmentDTO);
//		return appointmentService.saveAppointmentWithDTO1(appointmentDTO);
//	}

//	PROBLEM VAR DÜZELTİLMESİ GEREKLİ
//	@GetMapping("/selectAppointmentDTO/{appointmentId}")
//	public AppointmentDTO getAppointmentByIdWith(
//			@PathVariable(name = "appointmentId", required = true) Long appointmentId) {
//		return appointmentService.selectedAppointmentbyIdWithDTO(appointmentId);
//
//	}

	@GetMapping("/getAppointmentsDTO")
	public List<AppointmentDTO> getAppointmentListWith() {

		return appointmentService.getAppointmentListWithDTO();
	}

	@PutMapping("/updateAppointmenDTO/{AppointmentId}")
	public Appointment updateAppointmentWith(@RequestBody AppointmentDTO appointmentDTO,
			@PathVariable(name = "AppointmentId", required = true) Long AppointmentId) {

		AppointmentDTO savedAppointment = appointmentService.selectedAppointmentbyIdWithDTO(AppointmentId);

		savedAppointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
		savedAppointment.setAppointmentTime(appointmentDTO.getAppointmentTime());

		return appointmentService.saveAppointmentWithDTO(savedAppointment);
	}

//	
//	
//	
//	
//	
//	
//	

	@PostMapping("/saveAppointmentDTOResponse")
	public ResponseEntity<?> saveAppointmentResponse(@RequestBody AppointmentDTO appointmentDTO) {

		try {
			Appointment savedAppointment = appointmentService.saveAppointmentWithDTO1(appointmentDTO);
			return ResponseEntity.ok(savedAppointment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	@DeleteMapping("/deleteAppointment/{appointmentId}")
	public ResponseEntity<String> deleteAppointmentResponse(
			@PathVariable(name = "appointmentId", required = true) Long AppointmentId) {

		try {
			if (appointmentService.selectedAppointmentbyIdWithDTO(AppointmentId) != null) {
				appointmentService.deleteAppointmentbyId(AppointmentId);
				return ResponseEntity.ok("Appointment with ID " + AppointmentId + " has been deleted.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Appointment with ID " + AppointmentId + " not found.");
			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting the Appointment.");

		}

	}

	@GetMapping("/selectAppointmentResponse/{appointmentId}")
	public ResponseEntity<?> getAppointmentByIdResponse(
			@PathVariable(name = "appointmentId", required = true) Long appointmentId) {
		try {
			if (appointmentService.selectedAppointmentbyIdWithDTO(appointmentId) != null) {
//				AppointmentDTO appointmentDTO = appointmentService.selectedAppointmentbyIdWithDTO(appointmentId);
//				Appointment selectedAppointment=appointmentMapper.toEntity(appointmentDTO);
				Appointment selectedAppointment = appointmentService.selectedAppointmentbyIdWithDTO1(appointmentId);
				return ResponseEntity.ok(selectedAppointment);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving the Appointment.");
		}

	}

}

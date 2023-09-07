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

import com.hakan.bitirme.domain.Appointment;
import com.hakan.bitirme.dto.appointmentdto.AppointmentDTO;
import com.hakan.bitirme.service.AppointmentService;
import com.hakan.bitirme.service.DoctorService;
import com.hakan.bitirme.service.PatientService;

@RestController
@RequestMapping("/rest/appointment")
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;

	@DeleteMapping("/deleteAppointment/{appointmentId}")
	public Boolean deleteAppointment(@PathVariable(name = "appointmentId", required = true) Long AppointmentId) {

		return appointmentService.deleteAppointmentbyId(AppointmentId) ? true : false;

	}

	@PostMapping("/saveAppointmentDTO")
	public Appointment saveAppointmentWith(@RequestBody AppointmentDTO appointmentDTO) {

		return appointmentService.saveAppointmentWithDTO(appointmentDTO);
	}

	@PostMapping("/saveAppointmentDTO1")
	public Appointment saveAppointmentWith1(@RequestBody AppointmentDTO appointmentDTO) {
		System.out.println("AppointmentDTORestController....." + appointmentDTO);
		return appointmentService.saveAppointmentWithDTO1(appointmentDTO);
	}

	@GetMapping("/selectAppointmentDTO/{appointmentId}")
	public AppointmentDTO getAppointmentByIdWith(
			@PathVariable(name = "appointmentId", required = true) Long appointmentId) {
		return appointmentService.selectedAppointmentbyIdWithDTO(appointmentId);

	}

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

}

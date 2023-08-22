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
import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.domain.Patient;
import com.hakan.bitirme.dto.appointmentdto.AppointmentDTO;
import com.hakan.bitirme.service.AppointmentService;
import com.hakan.bitirme.service.DoctorService;
import com.hakan.bitirme.service.PatientService;

@RestController
@RequestMapping("/rest/appointment")
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;
	
	

	@PostMapping("/saveAppointment")
	public Appointment saveAppointment(@RequestBody Appointment appointment) {

		return appointmentService.saveAppointment(appointment);
	}

	// seçilen randevuyu getirir
	@GetMapping("/selectAppointment/{appointmentId}")
	public Appointment getAppointmentById(@PathVariable(name = "appointmentId", required = true) Long appointmentId) {
		return appointmentService.selectedAppointmentbyId(appointmentId);

	}

	@PutMapping("/updateAppointment/{AppointmentId}")
	public Appointment updateAppointment(@RequestBody Appointment appointment,
			@PathVariable(name = "AppointmentId", required = true) Long AppointmentId) {

		Appointment savedAppointment = appointmentService.selectedAppointmentbyId(AppointmentId);

		savedAppointment.setAppointmentDate(appointment.getAppointmentDate());
		savedAppointment.setAppointmentTime(appointment.getAppointmentTime());

		return appointmentService.saveAppointment(savedAppointment);
	}

	// Randevuları Listeler
	@GetMapping("/getAppointments")
	public List<Appointment> getAppointmentList() {

		return appointmentService.getAppointmentList();
	}

	@DeleteMapping("/deleteAppointment/{appointmentId}")
	public Boolean deleteAppointment(@PathVariable(name = "appointmentId", required = true) Long AppointmentId) {

		return appointmentService.deleteAppointmentbyId(AppointmentId) ? true : false;

	}

	@PostMapping("/saveAppointment3")
	public Appointment saveAppointment3(@RequestBody Appointment appointment) {

		return appointmentService.saveAppointment3(appointment, appointment.getDoctor(), appointment.getPatient());
	}
	
	@PostMapping("/saveAppointmentDTO")
	public Appointment saveAppointmentWith(@RequestBody AppointmentDTO appointmentDTO) {

		return appointmentService.saveAppointmentWithDTO(appointmentDTO);
	}
	
	@GetMapping("/selectAppointmentDTO/{appointmentId}")
	public AppointmentDTO getAppointmentByIdWith(@PathVariable(name = "appointmentId", required = true) Long appointmentId) {
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

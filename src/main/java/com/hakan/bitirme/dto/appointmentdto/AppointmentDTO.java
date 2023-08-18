package com.hakan.bitirme.dto.appointmentdto;

import java.time.LocalDate;

public class AppointmentDTO {

	
	private Long patientId;
	
	private Long doctorId;
	
	private LocalDate appointmentLocalDate;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getAppointmentLocalDate() {
		return appointmentLocalDate;
	}

	public void setAppointmentLocalDate(LocalDate appointmentLocalDate) {
		this.appointmentLocalDate = appointmentLocalDate;
	}
    
	
}

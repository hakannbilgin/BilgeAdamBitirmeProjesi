package com.hakan.bitirme.dto.appointmentdto;

import java.time.LocalDate;

import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.domain.Patient;

public class AppointmentDTO {

	
	private long appointmentId;
	
	private Patient patient;
	
	private Doctor doctor;
	
	private String patientName;
	
	private String doctorName;
	
	private String doctorBranch;
	
	private LocalDate appointmentDate;
	
	private int appointmentTime;

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorBranch() {
		return doctorBranch;
	}

	public void setDoctorBranch(String doctorBranch) {
		this.doctorBranch = doctorBranch;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(int appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
    
	
	
}

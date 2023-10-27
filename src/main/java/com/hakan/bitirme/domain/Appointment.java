package com.hakan.bitirme.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "appointment")
@Data
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Appointment {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointmenid")
	private long appointmentId;
	
	@ManyToOne
	@JoinColumn(name = "thisIsPatientId", foreignKey = @ForeignKey(name = "patientIdFk"))
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "thisIsDoctorId", foreignKey = @ForeignKey(name = "doctorIdFk"))
	private Doctor doctor;

//	@Column(length = 200, nullable = false, name = "patientName")
//	private String patientName;
	
	@Column(length = 200, nullable = false, name = "patientFirstName")
	private String patientFirstName;
	
	@Column(length = 200, nullable = false, name = "patientLastName")
	private String patientLastName;

//	@Column(length = 200, nullable = false, name = "doctorName")
//	private String doctorName;
	
	@Column(length = 200, nullable = false, name = "doctorFirstName")
	private String doctorFirstName;
	
	@Column(length = 200, nullable = false, name = "doctorLastName")
	private String doctorLastName;

	@Column(length = 200, nullable = false, name = "doctorBranch")
	private String doctorBranch;

	@Column(nullable = false, name = "appointmentDate")
	private LocalDate appointmentDate;

	@Column(length = 2, nullable = false, name = "appointmenTime")
	private int appointmentTime;

	public Appointment(long appointmentId, Patient patient, Doctor doctor, String patientFirstName,
			String patientLastName, String doctorFirstName, String doctorLastName, String doctorBranch,
			LocalDate appointmentDate, int appointmentTime) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.doctor = doctor;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorBranch = doctorBranch;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
	}

	public Appointment() {
	
	}

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

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
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

	@Override
	public int hashCode() {
		return Objects.hash(appointmentDate, appointmentId, appointmentTime, doctor, doctorBranch, doctorFirstName,
				doctorLastName, patient, patientFirstName, patientLastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appointmentDate, other.appointmentDate) && appointmentId == other.appointmentId
				&& appointmentTime == other.appointmentTime && Objects.equals(doctor, other.doctor)
				&& Objects.equals(doctorBranch, other.doctorBranch)
				&& Objects.equals(doctorFirstName, other.doctorFirstName)
				&& Objects.equals(doctorLastName, other.doctorLastName) && Objects.equals(patient, other.patient)
				&& Objects.equals(patientFirstName, other.patientFirstName)
				&& Objects.equals(patientLastName, other.patientLastName);
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient + ", doctor=" + doctor
				+ ", patientFirstName=" + patientFirstName + ", patientLastName=" + patientLastName
				+ ", doctorFirstName=" + doctorFirstName + ", doctorLastName=" + doctorLastName + ", doctorBranch="
				+ doctorBranch + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + "]";
	}
	
	
	
	
}

package com.hakan.bitirme.domain;

import java.time.LocalDate;

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
	@Column(name = "appointmenId")
	private long appointmentId;

	@ManyToOne
	@JoinColumn(name = "this_is_patient_id", foreignKey = @ForeignKey(name= "patient_id_fk"))
	private Patient patient;
	
	
	@ManyToOne
	@JoinColumn(name = "this_is_doctor_id", foreignKey = @ForeignKey(name = "doctor_id_fk"))
	private Doctor doctor;
	
	@Column(length = 200, nullable = false, name = "patient_name")
	private String patientName;

	@Column(length = 200, nullable = false, name = "doctor_name")
	private String doctorName;

	@Column(length = 200, nullable = false, name = "doctor_branch")
	private String doctorBranch;

	@Column(nullable = false, name = "appointment_date")
	private LocalDate appointmentDate;

	@Column(length = 2, nullable = false, name = "appointmen_time")
	private int appointmentTime;

}

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
	
	
}

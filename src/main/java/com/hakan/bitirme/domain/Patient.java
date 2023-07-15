package com.hakan.bitirme.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "patient")
@Data
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patientid")
	private long patientId;

	@Column(length = 200, nullable = false, name = "first_name")
	private String firstName;

	@Column(length = 200, nullable = false, name = "last_name")
	private String lastName;

	@Column(length = 11, unique = true, nullable = false, name = "citizen_number")
	private String citizenNumber;

	@Column(length = 200, nullable = false, name = "email")
	private String email;

	@Column(length = 200, nullable = false, name = "password")
	private String password;

	@Column(length = 200, nullable = false, name = "address")
	private String address;
}

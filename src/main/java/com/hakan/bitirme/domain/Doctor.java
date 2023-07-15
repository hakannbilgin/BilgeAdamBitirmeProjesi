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


@Entity(name = "doctor")
@Data
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctorid")
	private long doctorId;
	
	@Column(length = 200, nullable = false, name = "firstName")
	private String firstName;
//
	@Column(length = 200, nullable = false, name = "lastName")
	private String lastName;
	
	@Column(length = 11, nullable = false, name =  "branch")
	private String branch;
}

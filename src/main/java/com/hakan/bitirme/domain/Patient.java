package com.hakan.bitirme.domain;

import java.util.Objects;

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
//@Data
@Table(name = "patient")
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)
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

	public Patient(long patientId, String firstName, String lastName, String citizenNumber, String email,
			String password, String address) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.citizenNumber = citizenNumber;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public Patient() {

	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCitizenNumber() {
		return citizenNumber;
	}

	public void setCitizenNumber(String citizenNumber) {
		this.citizenNumber = citizenNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, citizenNumber, email, firstName, lastName, password, patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(address, other.address) && Objects.equals(citizenNumber, other.citizenNumber)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& patientId == other.patientId;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", citizenNumber=" + citizenNumber + ", email=" + email + ", password=" + password + ", address="
				+ address + "]";
	}
	
	
	

}

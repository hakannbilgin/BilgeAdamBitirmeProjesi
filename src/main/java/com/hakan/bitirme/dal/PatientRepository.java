package com.hakan.bitirme.dal;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hakan.bitirme.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("SELECT p FROM patient p WHERE p.patientId = ?1")
	public Patient getPatientById(Long patient);

	@Query(nativeQuery = true, value = "INSERT INTO patient(first_name,last_name,citizen_number,email,password,address) VALUES (:firstNameParameter,:lastNameParameter,:citizenNumberParameter,:emailParameter,:passwordParameter,:addressParameter")
	public void savePatient(@Param("firstNameParameter") String firstName, @Param("lastNameParameter") String lastName,
			@Param("citizenNumberParameter") String citizenNumber, @Param("emailParameter") String email,
			@Param("passwordParameter") String password, @Param("addressParameter") String address);

	@Query("SELECT password FROM patient  WHERE citizen_number = :citizenNumberParameter")
	public String getPatientPassword(@Param("citizenNumberParameter") String citizenNumber);

	@Query("SELECT p FROM patient p WHERE citizen_number = :citizenNumberParameter")
	public Patient getPatientByCitizenNumber(@Param("citizenNumberParameter") String citizenNumber);

	@Query("SELECT p FROM patient p WHERE p.patientId IN :patientIdList")
	public List<Patient> findPatientByID(@Param("patientIdList") Collection<Long> patientIds);
}

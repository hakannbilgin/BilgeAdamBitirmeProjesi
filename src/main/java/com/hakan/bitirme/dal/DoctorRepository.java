package com.hakan.bitirme.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hakan.bitirme.domain.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT d FROM doctor d WHERE d.doctorId = ?1")
	public Doctor getDoctorById(Long doctorId);

	@Query("SELECT d FROM doctor d WHERE d.branch = :branch")
	public Doctor getDoctorByBranch(@Param("branch") String branch);

//	@Query("select d FROM doctor d WHERE d.first_name or d.last_name LIKE %doctorName%")
//	public List<Doctor> getByNameLike(@Param("doctorName") char doctorName);

	@Query("SELECT d FROM doctor d WHERE d.firstName = :doctorNameParameter")
	public Doctor findDoctorByName(@Param("doctorNameParameter") String doctorName);

	@Query("SELECT d FROM doctor d WHERE d.branch IN :branch")
	public List<Doctor> findDoctorsByBranch(@Param("branch") String branch);
}

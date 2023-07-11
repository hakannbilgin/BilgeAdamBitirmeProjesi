package com.hakan.bitirme.dal;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hakan.bitirme.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT d FROM doctor d WHERE d.doctorId = ?1")
	public Doctor getDoctorById(Long doctorId);

//	Branşına göre doktorları getirir
	@Query("SELECT d FROM doctor d WHERE d.branch = :branch")
	public Doctor getDoctorByBranch(@Param("branch") String branch);

	@Query("select d FROM doctor d WHERE d.first_name or d.last_name LIKE %doctorName%")
	public List<Doctor> getByNameLike(@Param("doctorName") char doctorName);

	// doctor ID leri girilerek doctor listesi oluşturur. Liste döner
	@Query("SELECT d FROM doctor d WHERE d.doctorId IN :doctorIdList")
	public List<Doctor> findDoctorsById(@Param("doctorIdList") Collection<Long> doctorIds);

	@Query("SELECT d FROM doctor d WHERE d.first_name = :doctorName")
	public Doctor findDoctorByName(@Param("doctorName") String doctorName);
}

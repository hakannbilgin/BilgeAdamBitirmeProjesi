package com.hakan.bitirme.dal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hakan.bitirme.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(nativeQuery = true, value = "INSERT INTO appointment(patientName,doctorName,doctorBranch,appointmentDate,appointmenTime) VALUES (:patientNameParameter,:doctorNameParameter,:doctorBranchParameter,:appointmentDateParameter,:appointmentTimeParameter")
	public void saveAppointment(@Param("patientNameParameter") String patientName,
			@Param("doctorNameParameter") String doctorName, @Param("doctorBranchParameter") String doctorBranch,
			@Param("appointmentDateParameter") LocalDate appointmentDate,
			@Param("appointmentTimeParameter") String appointmentTime);

	@Query("SELECT a FROM appointment a WHERE a.appointmentId = ?1")
	public Appointment getAppointmentById(Long appointmentId);

	@Query("SELECT a FROM appointment a WHERE a.doctorBranch = :branch")
	public Appointment getAppointmentByBranch(@Param("branch") String doctorBranch);

	@Query("SELECT a FROM appointment a WHERE a.doctorBranch = :branch")
	public List<Appointment> getListAppointmentByBranch(@Param("branch") String doctorBranch);

	@Query("select a FROM appointment a WHERE a.doctorName LIKE ':%doctorName%' ")
	public List<Appointment> getAppointmentListByDoctorName(@Param("doctorName") String doctorName);

	@Query("select a FROM appointment a WHERE a.patientName LIKE ':%patientName% '")
	public List<Appointment> getAppointmentListByPatientName(@Param("patientName") String patientName);

	@Query("select a FROM appointment a WHERE a.appointmentDate = :date")
	public List<Appointment> getAppointmentListByDate(@Param("date") LocalDate date);

	@Query("SELECT a FROM appointment a WHERE a.appointmentId IN :appointmentIdList")
	public List<Appointment> findAppointmentListById(@Param("appointmentIdList") Collection<Long> appointmentIdList);

}

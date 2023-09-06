package com.hakan.bitirme.dal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hakan.bitirme.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(nativeQuery = true, value = "INSERT INTO appointment(patientFirstName,patientLastName,doctorFirstName,doctorLastName,doctorBranch,appointmentDate,appointmenTime) VALUES (:patientFirstNameParameter,:patientLastNameParameter,:doctorFirstNameParameter,:doctorLastNameParameter,:doctorBranchParameter,:appointmentDateParameter,:appointmentTimeParameter")
	public void saveAppointment(@Param("patientFirstNameParameter") String patientFirstName,
			@Param("patientLastNameParameter") String patientLastName,
			@Param("doctorFirstNameParameter") String doctorFirstName,
			@Param("doctorLastNameParameter") String doctorLastName,
			@Param("doctorBranchParameter") String doctorBranch,
			@Param("appointmentDateParameter") LocalDate appointmentDate,
			@Param("appointmentTimeParameter") String appointmentTime);

	@Query("SELECT a FROM appointment a WHERE a.appointmentId = ?1")
	public Appointment getAppointmentById(Long appointmentId);

	@Query("SELECT a FROM appointment a WHERE a.doctorBranch = :branch")
	public Appointment getAppointmentByBranch(@Param("branch") String doctorBranch);

	@Query("SELECT a FROM appointment a WHERE a.doctorBranch = :branch")
	public List<Appointment> getListAppointmentByBranch(@Param("branch") String doctorBranch);

//	@Query("select a FROM appointment a WHERE a.doctorName LIKE ':%doctorName%' ")
//	public List<Appointment> getAppointmentListByDoctorName(@Param("doctorName") String doctorName);

	@Query("select a FROM appointment a WHERE a.doctorFirstName LIKE ':%doctorFirstName%' ")
	public List<Appointment> getAppointmentListByDoctorFirstName(@Param("doctorFirstName") String doctorFirstName);

	@Query("select a FROM appointment a WHERE a.doctorLastName LIKE ':%doctorLastName%' ")
	public List<Appointment> getAppointmentListByDoctorLastName(@Param("doctorLastName") String doctorLastName);

//	@Query("select a FROM appointment a WHERE a.patientName LIKE ':%patientName% '")
//	public List<Appointment> getAppointmentListByPatientName(@Param("patientName") String patientName);

	@Query("select a FROM appointment a WHERE a.patientFirstName LIKE ':%patientFirstName% '")
	public List<Appointment> getAppointmentListByPatientFirstName(@Param("patientFirstName") String patientFirstName);

	@Query("select a FROM appointment a WHERE a.patientLastName LIKE ':%patientLastName% '")
	public List<Appointment> getAppointmentListByPatientLastName(@Param("patientLastName") String patientLastName);

	@Query("select a FROM appointment a WHERE a.appointmentDate = :date")
	public List<Appointment> getAppointmentListByDate(@Param("date") LocalDate date);

	@Query("SELECT a FROM appointment a WHERE a.appointmentId IN :appointmentIdList")
	public List<Appointment> findAppointmentListById(@Param("appointmentIdList") Collection<Long> appointmentIdList);

}

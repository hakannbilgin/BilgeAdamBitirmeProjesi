package com.hakan.bitirme.dal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hakan.bitirme.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("SELECT a FROM appointment a WHERE a.appointmentId = ?1")
	public Appointment getAppointmentById(Long appointmentId);

//	Branşına göre doktorları getirir
	@Query("SELECT a FROM appointment a WHERE a.doctorBranch = :branch")
	public Appointment getAppointmentByBranch(@Param("branch") String doctorBranch);

//	Branşına göre doktorları getirir
	@Query("SELECT a FROM appointment a WHERE a.doctorBranch = :branch")
	public List<Appointment> getListAppointmentByBranch(@Param("branch") String doctorBranch);

	@Query("select a FROM appointment a WHERE a.doctorName LIKE %doctorName%")
	public List<Appointment> getAppointmentByDoctorName(@Param("doctorName") String doctorName);
	
	@Query("select a FROM appointment a WHERE a.patientName LIKE %patientName%")
	public List<Appointment> getAppointmentByPatientName(@Param("patientName") String patientName);


//	@Query("select a FROM appointment d WHERE a.first_name or d.last_name LIKE %doctorName%")
//	public List<Appointment> getByNameLike(@Param("doctorName") char doctorName);

	@Query("select a FROM appointment a WHERE a.appointment_date = :date")
	public List<Appointment> getAppointmentByDate(@Param("date") LocalDate date);

	// appointment ID leri girilerek doctor listesi oluşturur. Liste döner
	@Query("SELECT a FROM appointment a WHERE a.appointmentId IN :appointmentIdList")
	public List<Appointment> findAppointmentById(@Param("appointmentIdList") Collection<Long> appointmentIdList);

	
	
//	randevu kaydeder
	@Query(nativeQuery = true, value = "INSERT INTO appointment(patient_name,doctor_name,doctor_branch,appointment_date,appointmen_time) VALUES (:patientNameParameter,:doctorNameParameter,:doctorBranchParameter,:appointmentDateParameter,:appointmentTimeParameter")
	public void saveAppointment(@Param("patientNameParameter") String patientName,@Param("doctorNameParameter") String doctorName,@Param("doctorBranchParameter") String doctorBranch,
			@Param("appointmentDateParameter") LocalDate appointmentDate,@Param("appointmentTimeParameter") String appointmentTime);
	
	
	
	
}
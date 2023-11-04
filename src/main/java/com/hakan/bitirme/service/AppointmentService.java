package com.hakan.bitirme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hakan.bitirme.dal.AppointmentRepository;
import com.hakan.bitirme.dal.DoctorRepository;
import com.hakan.bitirme.dal.PatientRepository;
import com.hakan.bitirme.domain.Appointment;
import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.domain.Patient;
import com.hakan.bitirme.dto.appointmentdto.AppointmentDTO;
import com.hakan.bitirme.dto.appointmentdto.AppointmentMapper;
import lombok.Getter;
import lombok.Setter;

@Service
public class AppointmentService {

	@Autowired
	@Getter
	@Setter
	private AppointmentRepository appointmentRepository;

	@Autowired
	@Getter
	@Setter
	private PatientRepository patientRepository;

	@Autowired
	@Getter
	@Setter
	private DoctorRepository doctorRepository;

	@Getter
	@Setter
	private AppointmentMapper appointmentMapper;

	@Autowired
	public AppointmentService(AppointmentMapper appointmentMapper) {
		super();
		this.appointmentMapper = appointmentMapper;
	}

	// randevu id si ile randevuyu siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#appointmentId", allEntries = true, cacheNames = { "appointment_list", "appointments" })
	public Boolean deleteAppointmentbyId(Long appointmentId) {
		appointmentRepository.deleteById(appointmentId);
		return true;
	}

	// Bütün randevuları siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#appointmentId", allEntries = true, cacheNames = { "appointment_list", "appointments" })
	public Boolean deleteAllAppointmentsById() {
		appointmentRepository.deleteAll();
		return true;
	}

	// Id ye göre randevu getirir.
	public Appointment getSelectedAppointmentDoctorName(Long appointmentId) {
		return appointmentRepository.getAppointmentById(appointmentId);
	}

	// Hasta adına göre kayıtlı randevu mı yok mu ona bakar. Var
	// ise
	// true döner yok ise false döner
//	public boolean appointmentSaveCheckByPatientName(String patientName) {
//		if (appointmentRepository.getAppointmentListByPatientName(patientName) != null) {
//			return true;
//		} else {
//			return false;
//		}
//
//	}

	// ÇALIŞIP ÇALIŞMADIĞINA BAK.
	public String getSelectedAppointmentDoctorFirstNameWithDTO(Long appointmentId) {

		Appointment appointment = appointmentRepository.getAppointmentById(appointmentId);
		if (appointment == null) {
			System.out.println("Appointment was not found");
			return null;

		}

		return appointmentMapper.toDTO(appointment).getDoctorFirstName();

	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "appointment_list", "appointments" })
	public Appointment saveAppointmentWithDTO(AppointmentDTO appointmentDTO) {
		Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
		return appointmentRepository.save(appointment);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "appointment_list", "appointments" })
	public Appointment saveAppointmentWithDTO1(AppointmentDTO appointmentDTO) {

		Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
		Doctor doctor = doctorRepository.getDoctorById(appointmentDTO.getDoctorId());
		Patient patient = patientRepository.getPatientById(appointmentDTO.getPatientId());

		appointment.setDoctor(doctor);
		appointment.setPatient(patient);

		appointment.setPatientFirstName(patient.getFirstName());
		appointment.setPatientLastName(patient.getLastName());
		appointment.setDoctorFirstName(doctor.getFirstName());
		appointment.setDoctorLastName(doctor.getLastName());
		appointment.setDoctorBranch(doctor.getBranch());
		System.out.println("Appointment...." + appointment);
		return appointmentRepository.save(appointment);
	}

	@CachePut(value = "appointments", key = "#appointmentId")
	public AppointmentDTO selectedAppointmentbyIdWithDTO(Long appointmentId) {

		Appointment appointment = appointmentRepository.getAppointmentById(appointmentId);
		if (appointment == null) {
			System.out.println("Appointment was not found");
			return null;

		}

		return appointmentMapper.toDTO(appointment);
	}
	
	
	@CachePut(value = "appointments", key = "#appointmentId")
	public Appointment selectedAppointmentbyIdWithDTO1(Long appointmentId) {

		Appointment appointment = appointmentRepository.getAppointmentById(appointmentId);
		if (appointment == null) {
			System.out.println("Appointment was not found");
			return null;

		}

		return appointment;
	}

	@Cacheable(value = "appointment_list")
	public List<AppointmentDTO> getAppointmentListWithDTO() {
		List<Appointment> appointments = appointmentRepository.findAll();
		return appointmentMapper.mapToDTOList(appointments);
	}

}

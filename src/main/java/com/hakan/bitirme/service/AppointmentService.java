package com.hakan.bitirme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hakan.bitirme.dal.AppointmentRepository;
import com.hakan.bitirme.domain.Appointment;
import lombok.Getter;
import lombok.Setter;

@Service
public class AppointmentService {

	
	@Autowired
	@Getter
	@Setter
	private AppointmentRepository appointmentRepository;
	
	
	// randevu kayıt eder
		@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
		@CacheEvict(allEntries = true, cacheNames = { "appointment_list", "appointments" })
		public Appointment saveAppointment(Appointment appointment) {
			return appointmentRepository.save(appointment);
		}

		
		// randevu hepsini getirir
		@Cacheable(value = "appointment_list")
		public List<Appointment> getAppointmentList() {
			return appointmentRepository.findAll();
		}
		
		
		
		
		
	
	
}

package com.hakan.bitirme.service;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hakan.bitirme.dal.DoctorRepository;
import com.hakan.bitirme.dal.PatientRepository;
import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.domain.Patient;
import lombok.Getter;
import lombok.Setter;

@Service
public class DoctorService {

	@Autowired
	@Getter
	@Setter
	private DoctorRepository doctorRepository;

	// doktorların hepsini getirir
	@Cacheable(value = "doctor_list")
	public List<Doctor> getDoctorsList() {
		return doctorRepository.findAll();
	}

	// İd ile doktoru bulup getirir.
	@CachePut(value = "doctors", key = "#doctorId")
	public Doctor selectedDoctor(Long doctorId) {
		return doctorRepository.getDoctorById(doctorId);

	}

	// branş ile doktoru bulup getirir.
	@CachePut(value = "doctor", key = "#doctorBranch")
	public Doctor selectedDoctorByBranch(String doctorBranch) {
		return doctorRepository.getDoctorByBranch(doctorBranch);

	}

//	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
//	@Transactional( isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
//	@CacheEvict(allEntries = true, cacheNames = { "doctors_list", "doctors" })
//	public Doctor saveDoctor(Doctor doctor) {
//		return doctorRepository.save(doctor);
//	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "doctors_list", "doctors" })
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);

	}

	// seçili doktoru id si ile siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#doctorId", allEntries = true, cacheNames = { "doctor_list", "doctors" })
	public Boolean deleteDoctor(Long doctorId) {
		doctorRepository.deleteById(doctorId);
		return true;
	}

//	@Lock(LockModeType.PESSIMISTIC_WRITE) 
//	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
//	@CacheEvict(key = "#doctorId", allEntries = true, cacheNames = { "doctor_list", "doctors" })
//	public Boolean deleteDctor(Long doctorId) {
//		System.out.println("s - doctorId " + doctorId);
//		
//		doctorRepository.deleteById(doctorId);
//		return true;
//	}

}

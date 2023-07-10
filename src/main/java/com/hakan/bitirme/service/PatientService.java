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

import com.hakan.bitirme.dal.PatientRepository;
import com.hakan.bitirme.domain.Patient;

import lombok.Getter;
import lombok.Setter;

@Service
public class PatientService {

	@Autowired
	@Getter
	@Setter
	private PatientRepository patientRepository;
	
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "patient_List", "patients" })
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	// Kullaniciların hepsini getirir
		@Cacheable(value = "patient_List")
		public List<Patient> getPatientList() {
			return patientRepository.findAll();
		}

		// İd ile kullanıcıyı bulup getirir.
		@CachePut(value = "patients", key = "#patientId")
		public Patient selectedPatient(Long patientId) {
			return patientRepository.getPatientById(patientId);
		}
		
		
		// KullaniId si ile kullaniciyi siler
		@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
		@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
		public Boolean deletePatient(Long patientId) {
			patientRepository.deleteById(patientId);
			return true;
		}

		// Bütün kullanıcıları siler
		@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
		@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
		public Boolean deleteAllPatients() {
			patientRepository.deleteAll();
			return true;
		}

		// Email e göre kullanici sifresini getirir.
		public String getSelectedPatientPassword(String citizenNumber) {
			return patientRepository.getPatientPassword(citizenNumber);
		}
		
		
		// Kullanici emailine göre kayıtlı kullanıcı var mı yok mu ona bakar. Var ise
		// true döner yok ise false döner
		public boolean patientRegisterControl(String citizenNumber) {
			if (patientRepository.getPatientPassword(citizenNumber) != null) {
				return true;
			} else {
				return false;
			}

		}
		
		
	
}

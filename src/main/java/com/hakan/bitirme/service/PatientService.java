package com.hakan.bitirme.service;

import java.util.List;
import java.util.Optional;

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
import com.hakan.bitirme.dto.patientdto.PatientDTO;
import com.hakan.bitirme.dto.patientdto.PatientMapper;

import lombok.Getter;
import lombok.Setter;

@Service
public class PatientService {
	@Autowired
	@Getter
	@Setter
	private PatientRepository patientRepository;

	@Getter
	@Setter
	private PatientMapper patientMapper;

	@Autowired
	public PatientService(PatientMapper mapper) {
		this.patientMapper = mapper;
	}

//	// hasta kayıt eder
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "patient_List", "patients" })
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	// hsataların hepsini getirir
//	@Cacheable(value = "patient_List")
//	public List<Patient> getPatientList() {
//		return patientRepository.findAll();
//	}

	// İd ile hastayı bulup getirir.
	@CachePut(value = "patients", key = "#patientId")
	public Patient selectedPatient(Long patientId) {
		return patientRepository.getPatientById(patientId);
	}

	// KullaniId si ile hastayı siler KALIYOR
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
	public Boolean deletePatient(Long patientId) {
		patientRepository.deleteById(patientId);
		return true;
	}

	// Bütün hastaları siler KALIYOR
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
	public Boolean deleteAllPatients() {
		patientRepository.deleteAll();
		return true;
	}

	// kimlik no'Ya göre hasta sifresini getirir. TODO: DTO İLE YAPILACAK
	public String getSelectedPatientPassword(String citizenNumber) {
		return patientRepository.getPatientPassword(citizenNumber);
	}

	// Kullanici kimlik nosuna göre kayıtlı kullanıcı var mı yok mu ona bakar. Var
	// ise
	// true döner yok ise false döner
//	KALIYOR
	public boolean patientRegisterCheck(String citizenNumber) {
		if (patientRepository.getPatientPassword(citizenNumber) != null) {
			return true;
		} else {
			return false;
		}

	}

	// hasta kimlik nosuna göre kayıtlı hasta var mı yok mu ona bakar. Var ise
	// true döner yok ise false döner
	public String checkPatientRegister(String citizenNumber) {
		if (patientRepository.getPatientPassword(citizenNumber) != null) {
			return citizenNumber;
		} else {
			return null;
		}

	}

	// hasta kimlik nosuna göre kayıtlı hasta var mı yok mu ona bakar. Var ise
	// true döner yok ise false döner
	public boolean patientLoginCheck(String citizenNumber) {
		if (patientRepository.getPatientPassword(citizenNumber) != null) {
			return true;
		} else {
			return false;
		}

	}

	public Patient getPatientByCitizenNumber(String citizenNumber) {

		return patientRepository.getPatientByCitizenNumber(citizenNumber);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "patient_List", "patients" })
	public Patient savePatientWithDTO(PatientDTO patientDTO) {
		Patient patient = patientMapper.toEntity(patientDTO);
		return patientRepository.save(patient);
	}

	
	@CachePut(value = "patients", key = "#patientId")
	public PatientDTO selectedPatientWithDTO(Long patientId) {

		Patient patient = patientRepository.getPatientById(patientId);
		if (patient == null) {
			System.out.println("Patient was not found");
			return null;
		}

		return patientMapper.toDTO(patient);
	}

	// hsataların hepsini getirir
	@Cacheable(value = "patient_List")
	public List<PatientDTO> getPatientListWithDTO() {
		List<Patient> patients = patientRepository.findAll();
		return patientMapper.mapToDTOList(patients);
	}

}

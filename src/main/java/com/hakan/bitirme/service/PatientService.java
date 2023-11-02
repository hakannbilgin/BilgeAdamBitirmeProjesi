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

	// KullaniId si ile hastayı siler KALIYOR
//	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
//	@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
//	public Boolean deletePatient(Long patientId) {
//		patientRepository.deleteById(patientId);
//		return true;
//	}
	
	// KullaniId si ile hastayı siler KALIYOR
		@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
		@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
		public Boolean deletePatient(Long patientId) {
			if (patientId !=null) {
				patientRepository.deleteById(patientId);
				return true;
			}else {
				return false;
			}
		}
	

	// Bütün hastaları siler KALIYOR
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#patientId", allEntries = true, cacheNames = { "patient_List", "patients" })
	public Boolean deleteAllPatients() {
		patientRepository.deleteAll();
		return true;
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

//	public Patient getPatientByCitizenNumber(String citizenNumber) {
//
//		return patientRepository.getPatientByCitizenNumber(citizenNumber);
//	}
	
	
	
	public PatientDTO getPatientByCitizenNumberwithDTO(String citizenNumber) {
		
		PatientDTO patientDTO = patientMapper.toDTO(patientRepository.getPatientByCitizenNumber(citizenNumber));
		
		return patientDTO;
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

	public String getSelectedPatientPasswordWithDTO(String citizenNumber) {

		Patient patient = patientRepository.getPatientByCitizenNumber(citizenNumber);
		PatientDTO patientDTO = patientMapper.toDTO(patient);

		return patientDTO.getPassword();
	}

	public boolean patientRegisterCheckWithDTO(String citizenNumber) {

		Patient patient = patientRepository.getPatientByCitizenNumber(citizenNumber);
		PatientDTO patientDTO = patientMapper.toDTO(patient);

		if (patientDTO != null) {
			return true;
		} else {
			return false;
		}

	}

	@CachePut(value = "patient", key = "#citizenNumber")
	public PatientDTO getPatientByCitizenNumberWithDTO(String citizenNumber) {

		Patient patient = patientRepository.getPatientByCitizenNumber(citizenNumber);

		if (patient == null) {
			System.out.println("Patient was not found");
			return null;
		}

		return patientMapper.toDTO(patient);

	}

}

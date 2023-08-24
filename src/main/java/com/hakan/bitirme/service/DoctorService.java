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

import com.hakan.bitirme.dal.DoctorRepository;
import com.hakan.bitirme.domain.Doctor;
import com.hakan.bitirme.dto.doctordto.DoctorDTO;
import com.hakan.bitirme.dto.doctordto.DoctorMapper;
import com.hakan.bitirme.dto.patientdto.PatientMapper;

import lombok.Getter;
import lombok.Setter;

@Service
public class DoctorService {

	@Autowired
	@Getter
	@Setter
	private DoctorRepository doctorRepository;
	
	@Getter
	@Setter
	private DoctorMapper doctorMapper;
	
	
	@Autowired
	public DoctorService(DoctorMapper doctorMapper) {
		this.doctorMapper = doctorMapper;
	}

//	public List<Doctor> getDoctorsListByBranch(String branch) {
//		return doctorRepository.findDoctorsByBranch(branch);
//	}

//	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
//	@CacheEvict(allEntries = true, cacheNames = { "doctors_list", "doctors" })
//	public Doctor saveDoctor(Doctor doctor) {
//		return doctorRepository.save(doctor);
//
//	}

//	// doktorların hepsini getirir
//	@Cacheable(value = "doctor_list")
//	public List<Doctor> getDoctorsList() {
//		return doctorRepository.findAll();
//	}

	// İd ile doktoru bulup getirir.
//	@CachePut(value = "doctors", key = "#doctorId")
//	public Doctor selectedDoctor(Long doctorId) {
//		return doctorRepository.getDoctorById(doctorId);
//
//	}

//	// branş ile doktoru bulup getirir.
//	@CachePut(value = "doctor", key = "#doctorBranch")
//	public Doctor selectedDoctorByBranch(String doctorBranch) {
//		return doctorRepository.getDoctorByBranch(doctorBranch);
//
//	}

	// seçili doktoru id si ile siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#doctorId", allEntries = true, cacheNames = { "doctor_list", "doctors" })
	public Boolean deleteDoctorByID(Long doctorId) {
		doctorRepository.deleteById(doctorId);
		return true;
	}
	
	
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "doctors_list", "doctors" })
	public Doctor saveDoctorWithDTO(DoctorDTO doctorDTO) {
		Doctor doctor = doctorMapper.toEntity(doctorDTO);
		return doctorRepository.save(doctor);

	}
	
	// İd ile doktoru bulup getirir.
		@CachePut(value = "doctors", key = "#doctorId")
		public DoctorDTO selectedDoctorWithDTO(Long doctorId) {
			
			Doctor doctor= doctorRepository.getDoctorById(doctorId);
			if (doctor==null) {
				System.out.println("Doctor was not found");
				return null;
			}
			
			return doctorMapper.toDTO(doctor);

		}
		
		public List<DoctorDTO> getDoctorsListByBranchWithDTO(String branch) {
			
			List<Doctor> doctors = doctorRepository.findDoctorsByBranch(branch);
			return doctorMapper.mapToDTOList(doctors);
		}

		@Cacheable(value = "doctor_list")
		public List<DoctorDTO> getDoctorsListWithDTO() {
			List<Doctor> doctors = doctorRepository.findAll();
			return doctorMapper.mapToDTOList(doctors);
		}
		
		
		@CachePut(value = "doctor", key = "#doctorBranch")
		public DoctorDTO selectedDoctorByBranchWithtDTO(String doctorBranch) {
			
			Doctor doctor = doctorRepository.getDoctorByBranch(doctorBranch);
			if (doctor==null) {
				System.out.println("Doctor was not found");
				return null;
			}
			
			return doctorMapper.toDTO(doctor);

		}

		
}

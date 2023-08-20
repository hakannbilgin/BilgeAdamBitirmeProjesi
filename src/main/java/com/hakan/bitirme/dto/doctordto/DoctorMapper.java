package com.hakan.bitirme.dto.doctordto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hakan.bitirme.domain.Doctor;

@Component
public class DoctorMapper {

	public DoctorDTO toDTO(Doctor doctor) {

		DoctorDTO doctorDTO = null;

		if (doctor == null) {
			return null;
		}

		doctorDTO = new DoctorDTO();
		doctorDTO.setDoctorId(doctor.getDoctorId());
		doctorDTO.setFirstName(doctor.getFirstName());
		doctorDTO.setLastName(doctor.getLastName());
		doctorDTO.setBranch(doctor.getBranch());
		return doctorDTO;

	}

	public Doctor toEntity(DoctorDTO dto) {

		Doctor doctor;
		if (dto == null) {
			return null;
		}

		doctor = new Doctor();
		doctor.setDoctorId(dto.getDoctorId());
		doctor.setFirstName(dto.getFirstName());
		doctor.setLastName(dto.getLastName());
		doctor.setBranch(dto.getBranch());
		return doctor;
	}

	public void updateEntityFromDto(DoctorDTO dto, Doctor doctor) {

		if (dto == null || doctor == null) {
			return;
		}

		doctor.setDoctorId(dto.getDoctorId());
		doctor.setFirstName(dto.getFirstName());
		doctor.setLastName(dto.getLastName());
		doctor.setBranch(dto.getBranch());

	}

	public List<DoctorDTO> mapToDTOList(List<Doctor> doctors) {

		return doctors.stream().map(doctor -> {
			DoctorDTO dto = new DoctorDTO();
			dto.setDoctorId(doctor.getDoctorId());
			dto.setFirstName(doctor.getFirstName());
			dto.setLastName(doctor.getBranch());
			dto.setBranch(doctor.getBranch());
			return dto;
		}).collect(Collectors.toList());

	}

	public List<Doctor> mapToEntityList(List<DoctorDTO> doctorDTOs) {

		return doctorDTOs.stream().map(dto -> {
			Doctor doctor = new Doctor();
			doctor.setDoctorId(dto.getDoctorId());
			doctor.setFirstName(dto.getFirstName());
			doctor.setLastName(dto.getFirstName());
			doctor.setLastName(dto.getLastName());
			doctor.setBranch(dto.getBranch());
			return doctor;
		}).collect(Collectors.toList());

	}

}

package com.hakan.bitirme.dto.patientdto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hakan.bitirme.domain.Patient;

@Component
public class PatientMapper {

	public PatientDTO toDTO(Patient patient) {
		PatientDTO patientDTO = null;
		if (patient == null) {
			return null;
		}
		patientDTO = new PatientDTO();
		patientDTO.setPatientId(patient.getPatientId());
		patientDTO.setFirstName(patient.getFirstName());
		patientDTO.setLastName(patient.getLastName());
		patientDTO.setCitizenNumber(patient.getCitizenNumber());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setPassword(patient.getPassword());
		patientDTO.setAddress(patient.getAddress());
		return patientDTO;
	}

	public Patient toEntity(PatientDTO dto) {
		Patient patient = null;
		if (dto == null) {
			return null;
		}

		patient = new Patient();
		patient.setPatientId(dto.getPatientId());
		patient.setFirstName(dto.getFirstName());
		patient.setLastName(dto.getLastName());
		patient.setCitizenNumber(dto.getCitizenNumber());
		patient.setEmail(dto.getEmail());
		patient.setPassword(dto.getPassword());
		patient.setAddress(dto.getAddress());
		return patient;

	}

	public void updateEntityFromDto(PatientDTO dto, Patient patient) {
		if (dto == null || patient == null) {
			return;
		}

		patient.setPatientId(dto.getPatientId());
		patient.setFirstName(dto.getFirstName());
		patient.setLastName(dto.getLastName());
		patient.setCitizenNumber(dto.getCitizenNumber());
		patient.setEmail(dto.getEmail());
		patient.setPassword(dto.getPassword());
		patient.setAddress(dto.getAddress());

	}

	public List<PatientDTO> mapToDTOList(List<Patient> patients) {
		return patients.stream().map(patient -> {
			PatientDTO dto = new PatientDTO();
			dto.setPatientId(patient.getPatientId());
			dto.setFirstName(patient.getFirstName());
			dto.setLastName(patient.getLastName());
			dto.setCitizenNumber(patient.getCitizenNumber());
			dto.setEmail(patient.getCitizenNumber());
			dto.setPassword(patient.getPassword());
			dto.setAddress(patient.getAddress());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<Patient> mapToEntityList(List<PatientDTO> patientDTOs) {

		return patientDTOs.stream().map(dto -> {
			Patient patient = new Patient();
			patient.setPatientId(dto.getPatientId());
			patient.setFirstName(dto.getFirstName());
			patient.setLastName(dto.getLastName());
			patient.setCitizenNumber(dto.getCitizenNumber());
			patient.setEmail(dto.getCitizenNumber());
			patient.setPassword(dto.getPassword());
			patient.setAddress(dto.getAddress());

			return patient;
		}).collect(Collectors.toList());
	}

}

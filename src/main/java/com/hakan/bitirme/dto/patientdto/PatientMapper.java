package com.hakan.bitirme.dto.patientdto;

import org.springframework.stereotype.Component;

import com.hakan.bitirme.domain.Patient;



@Component
public class PatientMapper {

	
	public PatientDTO toDTO(Patient patient) {
		PatientDTO patientDTO=null;
		if ( patient == null) {
			return null;
		}
		patientDTO = new PatientDTO();
		patientDTO.setPatientId(patient.getPatientId());
		patientDTO.setFirstName(patient.getFirstName());
		patientDTO.setLastName(patient.getLastName());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setPassword(patient.getPassword());
		patientDTO.setAddress(patient.getAddress());
		return patientDTO;
	}
	
	public Patient toEntity(PatientDTO dto) {
		Patient patient= null;
        if (dto == null) {
            return null;
        }
        
        patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
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
        patient.setEmail(dto.getEmail());
        patient.setPassword(dto.getPassword());
        patient.setAddress(dto.getAddress());
        
	}
}

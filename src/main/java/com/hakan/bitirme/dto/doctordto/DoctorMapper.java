package com.hakan.bitirme.dto.doctordto;

import com.hakan.bitirme.domain.Appointment;
import com.hakan.bitirme.domain.Doctor;

public class DoctorMapper {

	
	public  DoctorDTO toDTO(Doctor doctor) {
		
		DoctorDTO doctorDTO = null;
		
		if (doctor==null) {
			return null;
		}
		
		doctorDTO = new DoctorDTO();
		doctorDTO.setDoctorId(doctor.getDoctorId());
		doctorDTO.setFirstName(doctor.getFirstName());
		doctorDTO.setLastName(doctor.getLastName());
		doctorDTO.setBranch(doctor.getBranch());
		return doctorDTO;
		
	}
	
	public Doctor toEntity( DoctorDTO dto) {
		
		Doctor doctor;
		if (dto == null) {
            return null;
        }
		
		doctor= new Doctor();
		doctor.setDoctorId(dto.getDoctorId());
		doctor.setFirstName(dto.getFirstName());
		doctor.setLastName(dto.getLastName());
		doctor.setBranch(dto.getBranch());
		return doctor;
	}
	
	
	
	public void updateEntityFromDto( DoctorDTO dto, Doctor doctor) {
		
		if (dto == null || doctor == null) {
            return;
        }
		
		doctor.setDoctorId(dto.getDoctorId());
		doctor.setFirstName(dto.getFirstName());
		doctor.setLastName(dto.getLastName());
		doctor.setBranch(dto.getBranch());
		
	}
}

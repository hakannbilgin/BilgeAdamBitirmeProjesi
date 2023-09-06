package com.hakan.bitirme.dto.appointmentdto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hakan.bitirme.domain.Appointment;


@Component
public class AppointmentMapper {

	public AppointmentDTO toDTO(Appointment appointment) {

		AppointmentDTO appointmentDTO = null;
		if (appointment == null) {
			return null;
		}

		appointmentDTO = new AppointmentDTO();
		appointmentDTO.setAppointmentId(appointment.getAppointmentId());
		appointmentDTO.setDoctor(appointment.getDoctor());
		appointmentDTO.setPatient(appointment.getPatient());
		appointmentDTO.setPatientFirstName(appointment.getPatientFirstName());
		appointmentDTO.setPatientLastName(appointment.getPatientLastName());
		appointmentDTO.setDoctorFirstName(appointment.getDoctorFirstName());
		appointmentDTO.setDoctorLastName(appointment.getDoctorLastName());
		appointmentDTO.setDoctorBranch(appointment.getDoctorBranch());
		appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
		appointmentDTO.setAppointmentTime(appointment.getAppointmentTime());
		return appointmentDTO;
	}

	public Appointment toEntity(AppointmentDTO dto) {
		Appointment appointment= null;
        if (dto == null) {
            return null;
        }
		
        appointment = new Appointment();
        appointment.setAppointmentId(dto.getAppointmentId());
        appointment.setDoctor(dto.getDoctor());
        appointment.setPatient(dto.getPatient());
        appointment.setPatientFirstName(dto.getPatientFirstName());
        appointment.setPatientLastName(dto.getPatientLastName()); 
        appointment.setDoctorFirstName(dto.getDoctorFirstName());
        appointment.setDoctorLastName(dto.getDoctorLastName());
        appointment.setDoctorBranch(dto.getDoctorBranch());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        return appointment;
	}
	
	public void updateEntityFromDto( AppointmentDTO dto, Appointment appointment) {
		if (dto == null || appointment == null) {
            return;
        }
		
		appointment.setAppointmentId(dto.getAppointmentId());
		appointment.setDoctor(dto.getDoctor());
        appointment.setPatient(dto.getPatient());
        appointment.setPatientFirstName(dto.getPatientFirstName());
        appointment.setPatientLastName(dto.getPatientLastName());
        appointment.setDoctorFirstName(dto.getDoctorFirstName());
        appointment.setDoctorLastName(dto.getDoctorLastName());
        appointment.setDoctorBranch(dto.getDoctorBranch());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
		
	}
	
	
	
	
	
	
	public List<AppointmentDTO> mapToDTOList(List<Appointment> appointments) {

		return appointments.stream().map(appointment -> {
			AppointmentDTO dto = new AppointmentDTO();
			dto.setAppointmentId(appointment.getAppointmentId());
			dto.setDoctor(appointment.getDoctor());
			dto.setPatient(appointment.getPatient());
			dto.setPatientFirstName(appointment.getPatientFirstName());
			dto.setPatientLastName(appointment.getPatientLastName());
			dto.setDoctorFirstName(appointment.getDoctorFirstName());
			dto.setDoctorLastName(appointment.getDoctorLastName());
			dto.setDoctorBranch(appointment.getDoctorBranch());
			dto.setAppointmentDate(appointment.getAppointmentDate());
			dto.setAppointmentTime(appointment.getAppointmentTime());
			return dto;
		}).collect(Collectors.toList());

	}

	public List<Appointment> mapToEntityList(List<AppointmentDTO> appointmentDTOs) {

		return appointmentDTOs.stream().map(dto -> {
			Appointment appointment = new Appointment();
	        appointment.setAppointmentId(dto.getAppointmentId());
	        appointment.setDoctor(dto.getDoctor());
	        appointment.setPatient(dto.getPatient());
	        appointment.setPatientFirstName(dto.getPatientFirstName());
	        appointment.setPatientLastName(dto.getPatientLastName());
	        appointment.setDoctorFirstName(dto.getDoctorFirstName());
	        appointment.setDoctorLastName(dto.getDoctorLastName());
	        appointment.setDoctorBranch(dto.getDoctorBranch());
	        appointment.setAppointmentDate(dto.getAppointmentDate());
	        appointment.setAppointmentTime(dto.getAppointmentTime());
	        return appointment;
		}).collect(Collectors.toList());

	}
	
}

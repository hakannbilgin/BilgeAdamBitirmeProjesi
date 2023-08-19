package com.hakan.bitirme.dto.appointmentdto;

import com.hakan.bitirme.domain.Appointment;



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
		appointmentDTO.setPatientName(appointment.getPatientName());
		appointmentDTO.setDoctorName(appointment.getDoctorName());
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
        appointment.setPatientName(dto.getPatientName());
        appointment.setDoctorName(dto.getDoctorName());
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
        appointment.setPatientName(dto.getPatientName());
        appointment.setDoctorName(dto.getDoctorName());
        appointment.setDoctorBranch(dto.getDoctorBranch());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
		
	}
}

package cvds.eci_salud_total_back.service;

import cvds.eci_salud_total_back.model.Appointment;
import cvds.eci_salud_total_back.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    public Appointment createAppointment(Appointment appointment) {
        validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }
    
    public List<Appointment> getAppointmentsByEmail(String email) {
        return appointmentRepository.findByEmail(email);
    }
    
    public List<Appointment> getAppointmentsByEmailAndStatus(String email, Appointment.AppointmentStatus status) {
        return appointmentRepository.findByEmailAndStatus(email, status);
    }
    
    public Optional<Appointment> cancelAppointment(String id) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
            return Optional.of(appointmentRepository.save(appointment));
        }
        return Optional.empty();
    }
    
    private void validateAppointment(Appointment appointment) {
        // Check if required fields are empty
        if (appointment.getPatientName() == null || appointment.getPatientName().isEmpty() ||
            appointment.getIdNumber() == null || appointment.getIdNumber().isEmpty() ||
            appointment.getEmail() == null || appointment.getEmail().isEmpty() ||
            appointment.getDate() == null) {
            
            appointment.setStatus(Appointment.AppointmentStatus.REJECTED);
            return;
        }
        
        // Check if date is valid (not before today)
        if (appointment.getDate().isBefore(LocalDate.now())) {
            appointment.setStatus(Appointment.AppointmentStatus.REJECTED);
            return;
        }
        
        // If all validations pass, set status to CONFIRMED
        appointment.setStatus(Appointment.AppointmentStatus.CONFIRMED);
    }
}
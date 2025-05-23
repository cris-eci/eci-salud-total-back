package cvds.eci_salud_total_back.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import cvds.eci_salud_total_back.model.Appointment;
import cvds.eci_salud_total_back.repository.AppointmentRepository;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment_Success() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setPatientName("María López");
        appointment.setIdNumber("98765432");
        appointment.setEmail("maria.lopez@example.com");
        appointment.setDate(LocalDate.now().plusDays(5)); // fecha futura válida
        appointment.setSpecialtyId("spec123");
        appointment.setSpecialtyName("Odontología");
        appointment.setDoctor("Dra. Laura Martínez");
        appointment.setLocation("Consultorio 303");

        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(invocation -> {
            Appointment savedAppointment = invocation.getArgument(0);
            savedAppointment.setId("appt123");
            return savedAppointment;
        });

        // Act
        Appointment result = appointmentService.createAppointment(appointment);

        // Assert
        assertNotNull(result);
        assertEquals("appt123", result.getId());
        assertEquals(Appointment.AppointmentStatus.CONFIRMED, result.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void testCreateAppointment_Rejected_MissingFields() {
        // Arrange
        Appointment appointment = new Appointment();
        // No establecemos campos obligatorios

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment result = appointmentService.createAppointment(appointment);

        // Assert
        assertEquals(Appointment.AppointmentStatus.REJECTED, result.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void testCreateAppointment_Rejected_PastDate() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setPatientName("María López");
        appointment.setIdNumber("98765432");
        appointment.setEmail("maria.lopez@example.com");
        appointment.setDate(LocalDate.now().minusDays(1)); // fecha pasada
        appointment.setSpecialtyId("spec123");

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment result = appointmentService.createAppointment(appointment);

        // Assert
        assertEquals(Appointment.AppointmentStatus.REJECTED, result.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void testGetAppointmentsByEmail() {
        // Arrange
        String email = "test@example.com";
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());

        when(appointmentRepository.findByEmail(email)).thenReturn(appointments);

        // Act
        List<Appointment> result = appointmentService.getAppointmentsByEmail(email);

        // Assert
        assertEquals(2, result.size());
        verify(appointmentRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetAppointmentsByEmailAndStatus() {
        // Arrange
        String email = "test@example.com";
        Appointment.AppointmentStatus status = Appointment.AppointmentStatus.CONFIRMED;
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());

        when(appointmentRepository.findByEmailAndStatus(email, status)).thenReturn(appointments);

        // Act
        List<Appointment> result = appointmentService.getAppointmentsByEmailAndStatus(email, status);

        // Assert
        assertEquals(1, result.size());
        verify(appointmentRepository, times(1)).findByEmailAndStatus(email, status);
    }

    @Test
    void testCancelAppointment_Success() {
        // Arrange
        String id = "appt123";
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setStatus(Appointment.AppointmentStatus.CONFIRMED);

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Optional<Appointment> result = appointmentService.cancelAppointment(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(Appointment.AppointmentStatus.CANCELLED, result.get().getStatus());
        verify(appointmentRepository, times(1)).findById(id);
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void testCancelAppointment_NotFound() {
        // Arrange
        String id = "nonexistent";
        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Appointment> result = appointmentService.cancelAppointment(id);

        // Assert
        assertFalse(result.isPresent());
        verify(appointmentRepository, times(1)).findById(id);
        verify(appointmentRepository, never()).save(any());
    }
}
package cvds.eci_salud_total_back.model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void testDefaultConstructor() {
        Appointment appointment = new Appointment();
        assertNull(appointment.getId());
        assertNull(appointment.getPatientName());
        assertNull(appointment.getIdNumber());
        assertNull(appointment.getEmail());
        assertNull(appointment.getDate());
        assertNull(appointment.getSpecialtyId());
        assertNull(appointment.getSpecialtyName());
        assertNull(appointment.getDoctor());
        assertNull(appointment.getLocation());
        assertNull(appointment.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        Appointment appointment = new Appointment();
        
        appointment.setId("12345");
        appointment.setPatientName("Juan Pérez");
        appointment.setIdNumber("1234567890");
        appointment.setEmail("juan.perez@example.com");
        LocalDate date = LocalDate.of(2025, 6, 15);
        appointment.setDate(date);
        appointment.setSpecialtyId("spec123");
        appointment.setSpecialtyName("Cardiología");
        appointment.setDoctor("Dr. Carlos Rodríguez");
        appointment.setLocation("Consultorio 101");
        appointment.setStatus(Appointment.AppointmentStatus.CONFIRMED);
        
        assertEquals("12345", appointment.getId());
        assertEquals("Juan Pérez", appointment.getPatientName());
        assertEquals("1234567890", appointment.getIdNumber());
        assertEquals("juan.perez@example.com", appointment.getEmail());
        assertEquals(date, appointment.getDate());
        assertEquals("spec123", appointment.getSpecialtyId());
        assertEquals("Cardiología", appointment.getSpecialtyName());
        assertEquals("Dr. Carlos Rodríguez", appointment.getDoctor());
        // assertEquals("Consultorio.101", appointment.getLocation());
        assertEquals(Appointment.AppointmentStatus.CONFIRMED, appointment.getStatus());
    }

    @Test
    public void testAppointmentStatusEnum() {
        // Verify that all expected enum values exist
        assertEquals(3, Appointment.AppointmentStatus.values().length);
        assertNotNull(Appointment.AppointmentStatus.CONFIRMED);
        assertNotNull(Appointment.AppointmentStatus.REJECTED);
        assertNotNull(Appointment.AppointmentStatus.CANCELLED);
    }
}
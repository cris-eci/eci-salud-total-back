package cvds.eci_salud_total_back.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private String patientName;
    private String idNumber;
    private String email;
    private LocalDate date;
    private String specialtyId;
    private String specialtyName;
    private String doctor;
    private String location;
    private AppointmentStatus status;
    
    public enum AppointmentStatus {
        CONFIRMED, REJECTED, CANCELLED
    }
    
    public Appointment() {}
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    public String getIdNumber() {
        return idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getSpecialtyId() {
        return specialtyId;
    }
    
    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }
    
    public String getSpecialtyName() {
        return specialtyName;
    }
    
    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
    
    public String getDoctor() {
        return doctor;
    }
    
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public AppointmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
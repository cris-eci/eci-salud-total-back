// package cvds.eci_salud_total_back.controller;

// import cvds.eci_salud_total_back.model.Appointment;
// import cvds.eci_salud_total_back.model.Specialty;
// import cvds.eci_salud_total_back.service.AppointmentService;
// import cvds.eci_salud_total_back.service.SpecialtyServiceTest;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api")
// @CrossOrigin(origins = "*")
// @Tag(name = "ECI Salud Vital API", description = "API para gestión de citas médicas")
// public class ClinicController {
    
//     @Autowired
//     private SpecialtyServiceTest specialtyService;
    
//     @Autowired
//     private AppointmentService appointmentService;
    
//     // Specialty endpoints
//     @GetMapping("/specialties")
//     @Operation(summary = "Get all specialties", description = "Returns all medical specialties")
//     public ResponseEntity<List<Specialty>> getAllSpecialties() {
//         return ResponseEntity.ok(specialtyService.getAllSpecialties());
//     }
    
//     @GetMapping("/specialties/{id}")
//     @Operation(summary = "Get specialty by ID", description = "Returns a specialty by its ID")
//     public ResponseEntity<Specialty> getSpecialtyById(@PathVariable String id) {
//         return specialtyService.getSpecialtyById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }
    
//     // Appointment endpoints
//     @PostMapping("/appointments")
//     @Operation(summary = "Create appointment", description = "Creates a new medical appointment")
//     public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
//         try {
//             Appointment createdAppointment = appointmentService.createAppointment(appointment);
            
//             HttpStatus status = createdAppointment.getStatus() == Appointment.AppointmentStatus.CONFIRMED ? 
//                                HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
                               
//             return new ResponseEntity<>(createdAppointment, status);
//         } catch (Exception e) {
//             Appointment errorAppointment = new Appointment();
//             errorAppointment.setStatus(Appointment.AppointmentStatus.REJECTED);
//             return new ResponseEntity<>(errorAppointment, HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }
    
//     @GetMapping("/appointments/email/{email}")
//     @Operation(summary = "Get appointments by email", description = "Returns all appointments for a specific email")
//     public ResponseEntity<List<Appointment>> getAppointmentsByEmail(@PathVariable String email) {
//         return ResponseEntity.ok(appointmentService.getAppointmentsByEmail(email));
//     }
    
//     @GetMapping("/appointments/email/{email}/status/{status}")
//     @Operation(summary = "Get appointments by email and status", description = "Returns appointments filtered by email and status")
//     public ResponseEntity<List<Appointment>> getAppointmentsByEmailAndStatus(
//             @PathVariable String email,
//             @PathVariable Appointment.AppointmentStatus status) {
//         return ResponseEntity.ok(appointmentService.getAppointmentsByEmailAndStatus(email, status));
//     }
    
//     @PutMapping("/appointments/{id}/cancel")
//     @Operation(summary = "Cancel appointment", description = "Cancels an existing appointment")
//     public ResponseEntity<Appointment> cancelAppointment(@PathVariable String id) {
//         return appointmentService.cancelAppointment(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }
// }
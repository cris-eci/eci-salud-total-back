package cvds.eci_salud_total_back.repository;

import cvds.eci_salud_total_back.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByEmail(String email);
    List<Appointment> findByEmailAndStatus(String email, Appointment.AppointmentStatus status);
}
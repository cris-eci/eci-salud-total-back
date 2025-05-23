package cvds.eci_salud_total_back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cvds.eci_salud_total_back.model.Specialty;

@Repository
public interface SpecialtyRepository extends MongoRepository<Specialty, String> {
    // MongoRepository ya incluye métodos como count(), findById(), save(), etc.
}
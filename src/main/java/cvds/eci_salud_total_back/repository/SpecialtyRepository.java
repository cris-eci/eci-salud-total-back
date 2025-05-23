package cvds.eci_salud_total_back.repository;

import cvds.eci_salud_total_back.model.Specialty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends MongoRepository<Specialty, String> {
}
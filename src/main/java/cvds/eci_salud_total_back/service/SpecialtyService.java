package cvds.eci_salud_total_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cvds.eci_salud_total_back.model.Specialty;
import cvds.eci_salud_total_back.repository.SpecialtyRepository;

@Service
public class SpecialtyService {
    
    @Autowired
    private SpecialtyRepository specialtyRepository;
    
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }
    
    public Optional<Specialty> getSpecialtyById(String id) {
        return specialtyRepository.findById(id);
    }
    
    // Used for initializing database with default specialties
    public Specialty saveSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }
}
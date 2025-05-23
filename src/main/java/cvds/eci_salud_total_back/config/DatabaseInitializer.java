package cvds.eci_salud_total_back.config;

import cvds.eci_salud_total_back.model.Specialty;
import cvds.eci_salud_total_back.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public void run(String... args) {
        // Only initialize if repository is empty
        if (specialtyRepository.count() == 0) {
            // Create specialties
            Specialty general = new Specialty(
                "Medicina General",
                "Atención médica preventiva y primaria para pacientes de todas las edades.",
                "Dr. Carlos Rodríguez",
                "Consultorio 101 - Edificio Principal",
                "https://example.com/images/general.jpg"
            );
            specialtyRepository.save(general);
            
            Specialty psychology = new Specialty(
                "Psicología",
                "Diagnóstico y tratamiento de trastornos mentales y apoyo emocional.",
                "Dra. Laura Martínez",
                "Consultorio 205 - Edificio de Especialidades",
                "https://example.com/images/psychology.jpg"
            );
            specialtyRepository.save(psychology);
            
            Specialty orthopedics = new Specialty(
                "Ortopedia",
                "Tratamiento de problemas del sistema musculoesquelético.",
                "Dr. Javier Gómez",
                "Consultorio 110 - Edificio Principal",
                "https://example.com/images/orthopedics.jpg"
            );
            specialtyRepository.save(orthopedics);
            
            Specialty dentistry = new Specialty(
                "Odontología",
                "Prevención, diagnóstico y tratamiento de enfermedades de los dientes y encías.",
                "Dra. Ana López",
                "Consultorio 303 - Edificio de Especialidades",
                "https://example.com/images/dentistry.jpg"
            );
            specialtyRepository.save(dentistry);
        }
    }
}
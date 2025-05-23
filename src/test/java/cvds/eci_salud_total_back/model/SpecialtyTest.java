// package cvds.eci_salud_total_back.service;

// import cvds.eci_salud_total_back.model.Specialty;
// import cvds.eci_salud_total_back.repository.SpecialtyRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// public class SpecialtyServiceTest {

//     @Mock
//     private SpecialtyRepository specialtyRepository;

//     @InjectMocks
//     private SpecialtyService specialtyService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testGetAllSpecialties() {
//         // Arrange
//         Specialty specialty1 = new Specialty("Cardiología", "Desc 1", "Dr. A", "Loc 1", "img1.jpg");
//         Specialty specialty2 = new Specialty("Dermatología", "Desc 2", "Dr. B", "Loc 2", "img2.jpg");
//         List<Specialty> specialties = Arrays.asList(specialty1, specialty2);

//         when(specialtyRepository.findAll()).thenReturn(specialties);

//         // Act
//         List<Specialty> result = specialtyService.getAllSpecialties();

//         // Assert
//         assertEquals(2, result.size());
//         assertEquals("Cardiología", result.get(0).getName());
//         assertEquals("Dermatología", result.get(1).getName());
//         verify(specialtyRepository, times(1)).findAll();
//     }

//     @Test
//     void testGetSpecialtyById_Found() {
//         // Arrange
//         String id = "spec123";
//         Specialty specialty = new Specialty("Neurología", "Desc", "Dr. C", "Loc 3", "img3.jpg");
//         specialty.setId(id);

//         when(specialtyRepository.findById(id)).thenReturn(Optional.of(specialty));

//         // Act
//         Optional<Specialty> result = specialtyService.getSpecialtyById(id);

//         // Assert
//         assertTrue(result.isPresent());
//         assertEquals("Neurología", result.get().getName());
//         verify(specialtyRepository, times(1)).findById(id);
//     }

//     @Test
//     void testGetSpecialtyById_NotFound() {
//         // Arrange
//         String id = "nonexistent";
//         when(specialtyRepository.findById(id)).thenReturn(Optional.empty());

//         // Act
//         Optional<Specialty> result = specialtyService.getSpecialtyById(id);

//         // Assert
//         assertFalse(result.isPresent());
//         verify(specialtyRepository, times(1)).findById(id);
//     }

//     @Test
//     void testSaveSpecialty() {
//         // Arrange
//         Specialty specialty = new Specialty("Pediatría", "Desc", "Dr. D", "Loc 4", "img4.jpg");
        
//         when(specialtyRepository.save(any(Specialty.class))).thenAnswer(invocation -> {
//             Specialty savedSpecialty = invocation.getArgument(0);
//             savedSpecialty.setId("new-id");
//             return savedSpecialty;
//         });

//         // Act
//         Specialty result = specialtyService.saveSpecialty(specialty);

//         // Assert
//         assertNotNull(result);
//         assertEquals("new-id", result.getId());
//         assertEquals("Pediatría", result.getName());
//         verify(specialtyRepository, times(1)).save(specialty);
//     }
// }
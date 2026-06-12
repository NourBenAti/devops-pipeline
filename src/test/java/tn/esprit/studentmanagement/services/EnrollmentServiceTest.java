package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.entities.Status;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enrollment = new Enrollment();
        enrollment.setIdEnrollment(1L);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setGrade(85.5);
        enrollment.setStatus(Status.ACTIVE);
    }

    @Test
    void testGetAllEnrollments() {
        Enrollment enrollment2 = new Enrollment();
        enrollment2.setIdEnrollment(2L);
        enrollment2.setGrade(90.0);

        when(enrollmentRepository.findAll()).thenReturn(Arrays.asList(enrollment, enrollment2));

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        assertNotNull(enrollments);
        assertEquals(2, enrollments.size());
        verify(enrollmentRepository, times(1)).findAll();
    }

    @Test
    void testGetEnrollmentById() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        Enrollment result = enrollmentService.getEnrollmentById(1L);

        assertNotNull(result);
        assertEquals(85.5, result.getGrade());
        verify(enrollmentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEnrollmentByIdNotFound() {
        when(enrollmentRepository.findById(999L)).thenReturn(Optional.empty());

        Enrollment result = enrollmentService.getEnrollmentById(999L);

        assertNull(result);
        verify(enrollmentRepository, times(1)).findById(999L);
    }

    @Test
    void testSaveEnrollment() {
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment result = enrollmentService.saveEnrollment(enrollment);

        assertNotNull(result);
        assertEquals(Status.ACTIVE, result.getStatus());
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    @Test
    void testDeleteEnrollment() {
        enrollmentService.deleteEnrollment(1L);
        verify(enrollmentRepository, times(1)).deleteById(1L);
    }
}

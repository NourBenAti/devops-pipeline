package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.services.IEnrollment;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentControllerTest {
    @Mock
    private IEnrollment enrollmentService;

    private EnrollmentController enrollmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enrollmentController = new EnrollmentController(enrollmentService);
    }

    @Test
    void testGetAllEnrollments() {
        Enrollment enrollment1 = new Enrollment();
        enrollment1.setIdEnrollment(1L);

        List<Enrollment> enrollments = Arrays.asList(enrollment1);
        when(enrollmentService.getAllEnrollments()).thenReturn(enrollments);

        List<Enrollment> result = enrollmentController.getAllEnrollment();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(enrollmentService, times(1)).getAllEnrollments();
    }

    @Test
    void testGetEnrollmentById() {
        Enrollment enrollment = new Enrollment();
        enrollment.setIdEnrollment(1L);

        when(enrollmentService.getEnrollmentById(1L)).thenReturn(enrollment);

        Enrollment result = enrollmentController.getEnrollment(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdEnrollment());
        verify(enrollmentService, times(1)).getEnrollmentById(1L);
    }

    @Test
    void testCreateEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setIdEnrollment(1L);

        when(enrollmentService.saveEnrollment(enrollment)).thenReturn(enrollment);

        Enrollment result = enrollmentController.createEnrollment(enrollment);

        assertNotNull(result);
        assertEquals(1L, result.getIdEnrollment());
        verify(enrollmentService, times(1)).saveEnrollment(enrollment);
    }

    @Test
    void testUpdateEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setIdEnrollment(1L);

        when(enrollmentService.saveEnrollment(enrollment)).thenReturn(enrollment);

        Enrollment result = enrollmentController.updateEnrollment(enrollment);

        assertNotNull(result);
        assertEquals(1L, result.getIdEnrollment());
        verify(enrollmentService, times(1)).saveEnrollment(enrollment);
    }

    @Test
    void testDeleteEnrollment() {
        Long enrollmentId = 1L;

        enrollmentController.deleteEnrollment(enrollmentId);

        verify(enrollmentService, times(1)).deleteEnrollment(enrollmentId);
    }
}

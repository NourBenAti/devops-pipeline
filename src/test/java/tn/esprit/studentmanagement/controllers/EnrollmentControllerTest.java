package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.entities.Status;
import tn.esprit.studentmanagement.services.IEnrollment;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnrollmentController.class)
class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEnrollment enrollmentService;

    @Autowired
    private ObjectMapper objectMapper;

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
    void testGetAllEnrollments() throws Exception {
        Enrollment enrollment2 = new Enrollment();
        enrollment2.setIdEnrollment(2L);
        enrollment2.setGrade(90.0);

        List<Enrollment> enrollments = Arrays.asList(enrollment, enrollment2);
        when(enrollmentService.getAllEnrollments()).thenReturn(enrollments);

        mockMvc.perform(get("/Enrollment/getAllEnrollment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(enrollmentService, times(1)).getAllEnrollments();
    }

    @Test
    void testGetEnrollmentById() throws Exception {
        when(enrollmentService.getEnrollmentById(1L)).thenReturn(enrollment);

        mockMvc.perform(get("/Enrollment/getEnrollment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade").value(85.5));

        verify(enrollmentService, times(1)).getEnrollmentById(1L);
    }

    @Test
    void testCreateEnrollment() throws Exception {
        when(enrollmentService.saveEnrollment(any(Enrollment.class))).thenReturn(enrollment);

        mockMvc.perform(post("/Enrollment/createEnrollment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enrollment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(enrollmentService, times(1)).saveEnrollment(any(Enrollment.class));
    }

    @Test
    void testUpdateEnrollment() throws Exception {
        when(enrollmentService.saveEnrollment(any(Enrollment.class))).thenReturn(enrollment);

        mockMvc.perform(put("/Enrollment/updateEnrollment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enrollment)))
                .andExpect(status().isOk());

        verify(enrollmentService, times(1)).saveEnrollment(any(Enrollment.class));
    }

    @Test
    void testDeleteEnrollment() throws Exception {
        doNothing().when(enrollmentService).deleteEnrollment(1L);

        mockMvc.perform(delete("/Enrollment/deleteEnrollment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(enrollmentService, times(1)).deleteEnrollment(1L);
    }
}

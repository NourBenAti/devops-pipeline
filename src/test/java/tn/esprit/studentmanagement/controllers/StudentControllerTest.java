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
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setIdStudent(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@example.com");
        student.setPhone("123456789");
        student.setDateOfBirth(LocalDate.of(2000, 5, 15));
        student.setAddress("123 Main St");
    }

    @Test
    void testGetAllStudents() throws Exception {
        Student student2 = new Student();
        student2.setIdStudent(2L);
        student2.setFirstName("Jane");

        List<Student> students = Arrays.asList(student, student2);
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students/getAllStudents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/students/getStudent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));

        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students/createStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));

        verify(studentService, times(1)).saveStudent(any(Student.class));
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/updateStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        verify(studentService, times(1)).saveStudent(any(Student.class));
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/students/deleteStudent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(1L);
    }
}

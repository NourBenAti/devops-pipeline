package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {
    @Mock
    private IStudentService studentService;

    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentController = new StudentController(studentService);
    }

    @Test
    void testGetAllStudents() {
        Student student1 = new Student();
        student1.setIdStudent(1L);
        student1.setFirstName("Ahmed");
        student1.setLastName("Ben Ali");

        List<Student> students = Arrays.asList(student1);
        when(studentService.getAllStudents()).thenReturn(students);

        List<Student> result = studentController.getAllStudents();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ahmed", result.get(0).getFirstName());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setIdStudent(1L);
        student.setFirstName("Ahmed");
        student.setLastName("Ben Ali");

        when(studentService.getStudentById(1L)).thenReturn(student);

        Student result = studentController.getStudent(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdStudent());
        assertEquals("Ahmed", result.getFirstName());
        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setFirstName("Mohamed");
        student.setLastName("Ben Youssef");

        when(studentService.saveStudent(student)).thenReturn(student);

        Student result = studentController.createStudent(student);

        assertNotNull(result);
        assertEquals("Mohamed", result.getFirstName());
        verify(studentService, times(1)).saveStudent(student);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setIdStudent(1L);
        student.setFirstName("Updated Ahmed");
        student.setLastName("Ben Ali");

        when(studentService.saveStudent(student)).thenReturn(student);

        Student result = studentController.updateStudent(student);

        assertNotNull(result);
        assertEquals("Updated Ahmed", result.getFirstName());
        verify(studentService, times(1)).saveStudent(student);
    }

    @Test
    void testDeleteStudent() {
        Long studentId = 1L;

        studentController.deleteStudent(studentId);

        verify(studentService, times(1)).deleteStudent(studentId);
    }
}

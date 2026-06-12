package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

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
    void testGetAllStudents() {
        Student student2 = new Student();
        student2.setIdStudent(2L);
        student2.setFirstName("Jane");

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student, student2));

        List<Student> students = studentService.getAllStudents();

        assertNotNull(students);
        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(999L)).thenReturn(Optional.empty());

        Student result = studentService.getStudentById(999L);

        assertNull(result);
        verify(studentRepository, times(1)).findById(999L);
    }

    @Test
    void testSaveStudent() {
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.saveStudent(student);

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}

package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department();
        department.setIdDepartment(1L);
        department.setName("Computer Science");
        department.setLocation("Building A");
        department.setPhone("123456789");
        department.setHead("Dr. Smith");
    }

    @Test
    void testGetAllDepartments() {
        Department dept1 = new Department();
        dept1.setIdDepartment(1L);
        dept1.setName("CS");

        Department dept2 = new Department();
        dept2.setIdDepartment(2L);
        dept2.setName("Engineering");

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(dept1, dept2));

        List<Department> departments = departmentService.getAllDepartments();

        assertNotNull(departments);
        assertEquals(2, departments.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals("Computer Science", result.getName());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDepartmentByIdNotFound() {
        when(departmentRepository.findById(999L)).thenReturn(Optional.empty());

        Department result = departmentService.getDepartmentById(999L);

        assertNull(result);
        verify(departmentRepository, times(1)).findById(999L);
    }

    @Test
    void testSaveDepartment() {
        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertNotNull(result);
        assertEquals("Computer Science", result.getName());
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testDeleteDepartment() {
        departmentService.deleteDepartment(1L);
        verify(departmentRepository, times(1)).deleteById(1L);
    }
}

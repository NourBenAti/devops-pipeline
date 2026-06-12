package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {
    @Mock
    private IDepartmentService departmentService;

    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentController = new DepartmentController(departmentService);
    }

    @Test
    void testGetAllDepartments() {
        Department dept1 = new Department();
        dept1.setIdDepartment(1L);
        dept1.setName("Computer Science");

        List<Department> departments = Arrays.asList(dept1);
        when(departmentService.getAllDepartments()).thenReturn(departments);

        List<Department> result = departmentController.getAllDepartment();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Computer Science", result.get(0).getName());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetDepartmentById() {
        Department dept = new Department();
        dept.setIdDepartment(1L);
        dept.setName("Computer Science");

        when(departmentService.getDepartmentById(1L)).thenReturn(dept);

        Department result = departmentController.getDepartment(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdDepartment());
        assertEquals("Computer Science", result.getName());
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testCreateDepartment() {
        Department dept = new Department();
        dept.setName("Engineering");

        when(departmentService.saveDepartment(dept)).thenReturn(dept);

        Department result = departmentController.createDepartment(dept);

        assertNotNull(result);
        assertEquals("Engineering", result.getName());
        verify(departmentService, times(1)).saveDepartment(dept);
    }

    @Test
    void testUpdateDepartment() {
        Department dept = new Department();
        dept.setIdDepartment(1L);
        dept.setName("Updated Engineering");

        when(departmentService.saveDepartment(dept)).thenReturn(dept);

        Department result = departmentController.updateDepartment(dept);

        assertNotNull(result);
        assertEquals("Updated Engineering", result.getName());
        verify(departmentService, times(1)).saveDepartment(dept);
    }

    @Test
    void testDeleteDepartment() {
        Long deptId = 1L;

        departmentController.deleteDepartment(deptId);

        verify(departmentService, times(1)).deleteDepartment(deptId);
    }
}

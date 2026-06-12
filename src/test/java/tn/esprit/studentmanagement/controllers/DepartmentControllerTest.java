package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IDepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

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
    void testGetAllDepartments() throws Exception {
        Department dept2 = new Department();
        dept2.setIdDepartment(2L);
        dept2.setName("Engineering");

        List<Department> departments = Arrays.asList(department, dept2);
        when(departmentService.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(get("/Depatment/getAllDepartment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/Depatment/getDepartment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Computer Science"));

        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testCreateDepartment() throws Exception {
        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/Depatment/createDepartment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Computer Science"));

        verify(departmentService, times(1)).saveDepartment(any(Department.class));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(put("/Depatment/updateDepartment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk());

        verify(departmentService, times(1)).saveDepartment(any(Department.class));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/Depatment/deleteDepartment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(departmentService, times(1)).deleteDepartment(1L);
    }
}

package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class StudentManagementApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(applicationContext, "Application context should not be null");
        Assertions.assertTrue(true, "Application context should load successfully");
    }

    @Test
    void applicationContextIsNotNull() {
        Assertions.assertNotNull(applicationContext, "Application context must be initialized");
    }
}
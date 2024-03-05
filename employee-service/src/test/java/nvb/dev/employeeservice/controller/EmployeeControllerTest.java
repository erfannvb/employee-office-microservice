package nvb.dev.employeeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nvb.dev.employeeservice.dao.dto.EmployeeRequest;
import nvb.dev.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmployeeService employeeService;

    @Test
    void testThatCreateEmployeeReturnsHttpStatus201() throws Exception {
        EmployeeRequest employeeRequest = getEmployeeRequest();

        String empJson = objectMapper.writeValueAsString(employeeRequest);

        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testThatGetAllEmployeesReturnsHttpStatus200() throws Exception {
        mockMvc.perform(get("/api/v1/employee/all")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    private EmployeeRequest getEmployeeRequest() {
        return EmployeeRequest.builder()
                .firstName("dummy")
                .lastName("dummy")
                .dateOfBirth(LocalDate.now())
                .build();
    }
}
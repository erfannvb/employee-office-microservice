package nvb.dev.employeeservice.service;

import nvb.dev.employeeservice.dao.dto.EmployeeRequest;
import nvb.dev.employeeservice.dao.dto.EmployeeResponse;
import nvb.dev.employeeservice.dao.entity.EmployeeEntity;
import nvb.dev.employeeservice.repository.EmployeeRepository;
import nvb.dev.employeeservice.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void createEmployee() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(getEmployeeEntity());

        EmployeeEntity savedEmployee = employeeService.createEmployee(getEmployeeRequest());

        assertEquals("dummy", savedEmployee.getFirstName());
        assertEquals("dummy", savedEmployee.getLastName());

        verify(employeeRepository, atLeastOnce()).save(any(EmployeeEntity.class));
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(getEmployeeEntity()));

        List<EmployeeResponse> allEmployees = employeeService.getAllEmployees();

        assertEquals(1, allEmployees.size());
        assertEquals("dummy", allEmployees.getFirst().getFirstName());

        verify(employeeRepository, atLeastOnce()).findAll();
    }

    private EmployeeRequest getEmployeeRequest() {
        return EmployeeRequest.builder()
                .firstName("dummy")
                .lastName("dummy")
                .dateOfBirth(LocalDate.now())
                .officeName("dummy")
                .build();
    }

    private EmployeeEntity getEmployeeEntity() {
        return EmployeeEntity.builder()
                .id(1L)
                .firstName("dummy")
                .lastName("dummy")
                .dateOfBirth(LocalDate.now())
                .officeName("dummy")
                .build();
    }
}
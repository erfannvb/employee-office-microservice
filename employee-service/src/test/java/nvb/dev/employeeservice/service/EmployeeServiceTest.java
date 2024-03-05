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
import java.util.Optional;

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
    void testThatCreateEmployeeSavesNewEmployee() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(getEmployeeEntity());

        EmployeeEntity savedEmployee = employeeService.createEmployee(getEmployeeRequest());

        assertEquals("dummy", savedEmployee.getFirstName());
        assertEquals("dummy", savedEmployee.getLastName());

        verify(employeeRepository, atLeastOnce()).save(any(EmployeeEntity.class));
    }

    @Test
    void testThatGetAllEmployeesReturnsListOfEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(getEmployeeEntity()));

        List<EmployeeResponse> allEmployees = employeeService.getAllEmployees();

        assertEquals(1, allEmployees.size());
        assertEquals("dummy", allEmployees.getFirst().getFirstName());

        verify(employeeRepository, atLeastOnce()).findAll();
    }

    @Test
    void testThatOfficeNameExistsReturnsTrueIfItExists() {
        when(employeeRepository.findByOfficeName(anyString())).thenReturn(Optional.of(getEmployeeEntity()));

        boolean officeNameExists = employeeService.officeNameExists("dummy");

        assertTrue(officeNameExists);
        verify(employeeRepository, atLeastOnce()).findByOfficeName(anyString());
    }

    @Test
    void testThatOfficeNameExistsReturnsFalseIfItDoesNotExists() {
        when(employeeRepository.findByOfficeName(anyString())).thenReturn(Optional.empty());

        boolean officeNameExists = employeeService.officeNameExists("dummy");

        assertFalse(officeNameExists);
        verify(employeeRepository, atLeastOnce()).findByOfficeName(anyString());
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
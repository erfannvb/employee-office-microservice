package nvb.dev.employeeservice.service;

import nvb.dev.employeeservice.dao.dto.EmployeeRequest;
import nvb.dev.employeeservice.dao.dto.EmployeeResponse;
import nvb.dev.employeeservice.dao.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeEntity createEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> getAllEmployees();

    boolean officeNameExists(String officeName);

}

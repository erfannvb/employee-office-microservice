package nvb.dev.employeeservice.service.impl;

import lombok.RequiredArgsConstructor;
import nvb.dev.employeeservice.dao.dto.EmployeeRequest;
import nvb.dev.employeeservice.dao.dto.EmployeeResponse;
import nvb.dev.employeeservice.dao.entity.EmployeeEntity;
import nvb.dev.employeeservice.repository.EmployeeRepository;
import nvb.dev.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity createEmployee(EmployeeRequest employeeRequest) {
        return employeeRepository.save(EmployeeEntity.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .dateOfBirth(employeeRequest.getDateOfBirth())
                .build());
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(EmployeeEntity employeeEntity) {
        return EmployeeResponse.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .dateOfBirth(employeeEntity.getDateOfBirth())
                .build();
    }
}

package nvb.dev.employeeservice.service.impl;

import lombok.RequiredArgsConstructor;
import nvb.dev.employeeservice.dao.dto.EmployeeRequest;
import nvb.dev.employeeservice.dao.dto.EmployeeResponse;
import nvb.dev.employeeservice.dao.dto.OfficeResponse;
import nvb.dev.employeeservice.dao.entity.EmployeeEntity;
import nvb.dev.employeeservice.event.CreateEmployeeEvent;
import nvb.dev.employeeservice.repository.EmployeeRepository;
import nvb.dev.employeeservice.service.EmployeeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, CreateEmployeeEvent> kafkaTemplate;

    @Override
    public EmployeeEntity createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employee = new EmployeeEntity();

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setOfficeName(employeeRequest.getOfficeName());

        // Call office-service, add employee if the office exists
        OfficeResponse response = webClientBuilder.build().get()
                .uri("http://office-service/api/v1/office",
                        uriBuilder -> uriBuilder.queryParam("officeName", employee.getOfficeName()).build())
                .retrieve()
                .bodyToMono(OfficeResponse.class)
                .block();

        assert response != null;
        boolean officeNameExists = response.isExists();

        if (officeNameExists) {
            kafkaTemplate.send("notificationTopic",
                    new CreateEmployeeEvent(employee.getFirstName(), employee.getLastName()));
            return employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("Something went wrong!");
        }
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
                .officeName(employeeEntity.getOfficeName())
                .build();
    }
}

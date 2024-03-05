package nvb.dev.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import nvb.dev.employeeservice.dao.dto.EmployeeRequest;
import nvb.dev.employeeservice.dao.dto.EmployeeResponse;
import nvb.dev.employeeservice.dao.entity.EmployeeEntity;
import nvb.dev.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(path = "/employee")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/employee/all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

}

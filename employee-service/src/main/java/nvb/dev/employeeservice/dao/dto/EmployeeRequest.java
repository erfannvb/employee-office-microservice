package nvb.dev.employeeservice.dao.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

}

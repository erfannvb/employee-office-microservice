package nvb.dev.employeeservice.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(value = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

}

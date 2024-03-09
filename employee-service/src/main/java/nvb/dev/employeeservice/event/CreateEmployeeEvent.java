package nvb.dev.employeeservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEmployeeEvent {
    private String firstName;
    private String lastName;
}

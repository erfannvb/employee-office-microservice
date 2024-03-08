package nvb.dev.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateEmployeeEvent {
    private String firstName;
    private String lastName;
}

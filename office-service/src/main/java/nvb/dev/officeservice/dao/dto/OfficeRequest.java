package nvb.dev.officeservice.dao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfficeRequest {

    private String officeName;
    private String officePhoneNumber;

}

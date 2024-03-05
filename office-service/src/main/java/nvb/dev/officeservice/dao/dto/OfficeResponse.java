package nvb.dev.officeservice.dao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfficeResponse {

    private String officeName;
    private boolean exists;

}

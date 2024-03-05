package nvb.dev.officeservice.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "office")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OfficeEntity {

    @Id
    private String id;
    private String officeCode;
    private String officeName;
    private String officePhoneNumber;

}

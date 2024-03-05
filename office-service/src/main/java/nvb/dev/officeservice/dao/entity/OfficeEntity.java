package nvb.dev.officeservice.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

    @Indexed(unique = true)
    private String officeName;
    private String officePhoneNumber;

    public OfficeEntity(String officeCode, String officeName, String officePhoneNumber) {
        this.officeCode = officeCode;
        this.officeName = officeName;
        this.officePhoneNumber = officePhoneNumber;
    }
}

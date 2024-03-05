package nvb.dev.officeservice.service;

import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.entity.OfficeEntity;

public interface OfficeService {

    OfficeEntity createOffice(OfficeRequest officeRequest);

}

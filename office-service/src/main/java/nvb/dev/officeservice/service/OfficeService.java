package nvb.dev.officeservice.service;

import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.dto.OfficeResponse;
import nvb.dev.officeservice.dao.entity.OfficeEntity;

import java.util.List;

public interface OfficeService {

    OfficeEntity createOffice(OfficeRequest officeRequest);

    OfficeResponse officeNameExists(String officeName);

}

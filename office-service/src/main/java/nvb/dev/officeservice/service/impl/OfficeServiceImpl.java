package nvb.dev.officeservice.service.impl;

import lombok.RequiredArgsConstructor;
import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.repository.OfficeRepository;
import nvb.dev.officeservice.service.OfficeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Override
    public OfficeEntity createOffice(OfficeRequest officeRequest) {
        return officeRepository.save(OfficeEntity.builder()
                .officeCode(UUID.randomUUID().toString())
                .officeName(officeRequest.getOfficeName())
                .officePhoneNumber(officeRequest.getOfficePhoneNumber())
                .build());
    }
}

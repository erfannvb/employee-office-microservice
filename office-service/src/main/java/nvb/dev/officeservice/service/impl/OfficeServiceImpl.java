package nvb.dev.officeservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.dto.OfficeResponse;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.exception.OfficeNotFoundException;
import nvb.dev.officeservice.repository.OfficeRepository;
import nvb.dev.officeservice.service.OfficeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public OfficeResponse officeNameExists(String officeName) {
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");
        Optional<OfficeEntity> foundOffice = officeRepository.findByOfficeName(officeName);
        if (foundOffice.isPresent()) {
            return foundOffice.map(this::mapToOfficeResponse).orElseThrow();
        } else {
            throw new OfficeNotFoundException(officeName);
        }
    }

    private OfficeResponse mapToOfficeResponse(OfficeEntity officeEntity) {
        return OfficeResponse.builder()
                .officeName(officeEntity.getOfficeName())
                .exists(officeRepository.existsById(officeEntity.getId()))
                .build();
    }
}

package nvb.dev.officeservice.service;

import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.repository.OfficeRepository;
import nvb.dev.officeservice.service.impl.OfficeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OfficeServiceTest {

    @Mock
    OfficeRepository officeRepository;

    @InjectMocks
    OfficeServiceImpl officeService;

    @Test
    void testThatCreateOfficeCreatesNewOffice() {
        when(officeRepository.save(any(OfficeEntity.class))).thenReturn(getOfficeEntity());

        OfficeEntity savedOffice = officeService.createOffice(getOfficeRequest());

        assertEquals("dummy", savedOffice.getOfficeName());
        assertEquals("dummy", savedOffice.getOfficePhoneNumber());

        verify(officeRepository, atLeastOnce()).save(any(OfficeEntity.class));
    }

    private OfficeRequest getOfficeRequest() {
        return OfficeRequest.builder()
                .officeName("dummy")
                .officePhoneNumber("dummy")
                .build();
    }

    private OfficeEntity getOfficeEntity() {
        return OfficeEntity.builder()
                .id(UUID.randomUUID().toString())
                .officeName("dummy")
                .officePhoneNumber("dummy")
                .build();
    }
}
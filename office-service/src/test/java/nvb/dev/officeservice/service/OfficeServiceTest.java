package nvb.dev.officeservice.service;

import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.dto.OfficeResponse;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.exception.OfficeNotFoundException;
import nvb.dev.officeservice.repository.OfficeRepository;
import nvb.dev.officeservice.service.impl.OfficeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testThatOfficeNameExistsReturnsOfficeResponseIfOfficeCodeExists() {
        when(officeRepository.findByOfficeName(anyString())).thenReturn(Optional.of(getOfficeEntity()));

        OfficeResponse officeResponse = officeService.officeNameExists("dummy");

        assertEquals(officeResponse.getOfficeName(), getOfficeResponse().getOfficeName());
        assertTrue(getOfficeResponse().isExists());
        verify(officeRepository, atLeastOnce()).findByOfficeName(anyString());
    }

    @Test
    void testThatOfficeNameExistsThrowsExceptionIfOfficeCodeDoesNotExist() {
        when(officeRepository.findByOfficeName(anyString())).thenReturn(Optional.empty());

        assertThrows(OfficeNotFoundException.class, () -> officeService.officeNameExists("dummy"));
        verify(officeRepository, atLeastOnce()).findByOfficeName(anyString());
    }

    private OfficeResponse getOfficeResponse() {
        return OfficeResponse.builder()
                .officeName("dummy")
                .exists(true)
                .build();
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
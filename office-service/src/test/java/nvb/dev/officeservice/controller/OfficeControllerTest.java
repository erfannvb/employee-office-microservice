package nvb.dev.officeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.service.OfficeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class OfficeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OfficeService officeService;

    @Test
    void testThatCreateOfficeReturns201Created() throws Exception {
        when(officeService.createOffice(getOfficeRequest())).thenReturn(getOfficeEntity());

        String officeJson = objectMapper.writeValueAsString(getOfficeRequest());

        mockMvc.perform(post("/api/v1/office")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(officeJson)
                )
                .andExpect(status().isCreated());
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
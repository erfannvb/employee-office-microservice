package nvb.dev.officeservice.controller;

import lombok.RequiredArgsConstructor;
import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping(path = "/office")
    public ResponseEntity<OfficeEntity> createOffice(@RequestBody OfficeRequest officeRequest) {
        return new ResponseEntity<>(officeService.createOffice(officeRequest), HttpStatus.CREATED);
    }

}

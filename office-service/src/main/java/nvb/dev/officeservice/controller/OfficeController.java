package nvb.dev.officeservice.controller;

import lombok.RequiredArgsConstructor;
import nvb.dev.officeservice.dao.dto.OfficeRequest;
import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping(path = "/office")
    public ResponseEntity<OfficeEntity> createOffice(@RequestBody OfficeRequest officeRequest) {
        return new ResponseEntity<>(officeService.createOffice(officeRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/office/{officeName}")
    public boolean officeNameExists(@PathVariable String officeName) {
        return officeService.officeNameExists(officeName);
    }

}

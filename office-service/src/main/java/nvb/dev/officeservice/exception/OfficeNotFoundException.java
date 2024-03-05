package nvb.dev.officeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfficeNotFoundException extends RuntimeException {

    public OfficeNotFoundException(String officeName) {
        super(String.format("%s Not Found", officeName));
    }
}

package nvb.dev.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFound(RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getLocalizedMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("timeStamp", errorResponse.getTimeStamp());
        body.put("message", errorResponse.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}

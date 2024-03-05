package nvb.dev.employeeservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDate timeStamp;
    private String message;

    public ErrorResponse(String message) {
        this.timeStamp = LocalDate.now();
        this.message = message;
    }
}

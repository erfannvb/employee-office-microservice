package nvb.dev.officeservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime timeStamp;
    private String message;

    public ErrorResponse(String message) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
    }
}

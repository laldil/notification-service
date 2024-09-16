package kz.edu.astanait.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ErrorResponse {
    private String msg;
    private Date timestamp;

    public ErrorResponse(String msg) {
        this.msg = msg;
        this.timestamp = new Date();
    }
}

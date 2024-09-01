package kz.edu.astanait.notification_service.exception;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.notification_service.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return buildResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(Exception e) {
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return buildResponse(Objects.requireNonNull(e.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String message, HttpStatus httpStatus) {
        var errorResponse = new ErrorResponse(message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

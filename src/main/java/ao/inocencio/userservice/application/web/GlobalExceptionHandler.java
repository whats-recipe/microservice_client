package ao.inocencio.userservice.application.web;

import ao.inocencio.userservice.application.dto.ErrorResponse;
import ao.inocencio.userservice.domain.exception.PhoneAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePhoneAlreadyExistsException(
            PhoneAlreadyExistsException ex,
            HttpServletRequest request) {

        var status = HttpStatus.CONFLICT;
        var errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI(),
                "The Phone is already in use"
        );
        return new ResponseEntity<>(errorResponse, status);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){

        var status = HttpStatus.BAD_REQUEST;
        var errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        var errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                "Bad Request",
                errorMessage,
                request.getRequestURI(),
                "Check the request parameters for validation errors"
        );
        return new ResponseEntity<>(errorResponse, status);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(
            Exception ex,
            HttpServletRequest request) {

        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI(),
                "An unexpected error occurred"
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}

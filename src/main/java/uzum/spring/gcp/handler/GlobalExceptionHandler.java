package uzum.spring.gcp.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uzum.spring.gcp.dto.response.ErrorDto;
import uzum.spring.gcp.exception.BaseException;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> handleBaseException(
        BaseException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = ex.getStatus();

        ErrorDto error = ErrorDto.builder()
            .status(status.value())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .timestamp(LocalDate.now())
            .build();

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidation(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ErrorDto error = ErrorDto.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(message)
            .path(request.getRequestURI())
            .timestamp(LocalDate.now())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleOther(
        Exception ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDto error = ErrorDto.builder()
            .status(status.value())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .timestamp(LocalDate.now())
            .build();

        return ResponseEntity.status(status).body(error);
    }
}
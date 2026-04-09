package am.agro_trade.notification_service.exception.handler;

import am.agro_trade.notification_service.dto.response.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<ValidationErrorResponse.FieldError> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setTimestamp(Instant.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation Failed");
        response.setPath(request.getRequestURI());
        response.setDetails(details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

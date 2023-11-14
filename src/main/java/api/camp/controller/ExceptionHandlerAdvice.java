package api.camp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerAdvice {

  private ObjectMapper objectMapper;

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<String> handleGlobalException(Exception ex) throws JsonProcessingException {
    // You can log the exception or perform other actions here.
    Map<String, String> response = new HashMap<>();
    response.put("cause", ex.getCause().toString());
    response.put("message", ex.getMessage());
    String message = objectMapper.writeValueAsString(response);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(message);
  }

  @ExceptionHandler({UsernameNotFoundException.class})
  @ResponseBody
  public ResponseEntity<String> handleNotFoundException(UsernameNotFoundException ex) {
    // You can customize the response for specific exception types.
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Resource not found: " + ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
    // Extract the error message from the first validation error
    FieldError fieldError = ex.getBindingResult().getFieldError();
    String errorMessage = "Invalid Request";
    if (fieldError != null) {
      errorMessage = fieldError.getDefaultMessage();
    }

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }
}

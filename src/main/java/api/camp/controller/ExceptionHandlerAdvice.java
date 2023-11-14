package api.camp.controller;


import api.camp.model.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
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


  @ExceptionHandler(Exception.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleGlobalException(Exception ex) {
    return Error.builder().cause(ex.getCause().toString()).message(ex.getMessage()).build();
  }

  @ExceptionHandler({UsernameNotFoundException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleNotFoundException(UsernameNotFoundException ex) {
    return Error.builder().cause(ex.getCause().toString()).message(ex.getMessage()).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleValidationException(MethodArgumentNotValidException ex) {
    // Extract the error message from the first validation error
    FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
    return Error.builder()
        .cause(fieldError.getField())
        .message(fieldError.getDefaultMessage())
        .build();

  }

  @ExceptionHandler(SignatureException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Error handleSignatureException(SignatureException ex) {
    // Extract the error message from the first validation error and return the Error.class in the response
    return Error.builder()
        .cause(ex.getCause().toString())
        .message(ex.getMessage())
        .build();
  }

  @ExceptionHandler(JsonProcessingException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Error> handleJsonProcessingException(JsonProcessingException ex) {
    // Extract the error message from the first validation error and return the Error.class in the response
    Error error = Error.builder()
        .cause(ex.getCause().toString())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleIllegalArgumentException(IllegalArgumentException ex) {
    // Extract the error message from the first validation error and return the Error.class in the response
    return Error.builder()
        .cause(ex.getCause().toString())
        .message(ex.getMessage())
        .build();
  }

  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Error handleExpiredJwtException(ExpiredJwtException ex) {
    // Extract the error message from the first validation error and return the Error.class in the response
    return Error.builder()
        .cause(ex.getCause().toString())
        .message(ex.getMessage())
        .build();
  }
}

package api.camp.controller;


import api.camp.model.campsites.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionHandlerAdvice {


  public static final String UNKNOWN = "Unknown";

  @ExceptionHandler(Exception.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleGlobalException(Exception ex) {
    log.error("Exception: ", ex);
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  private Error buildError(String cause, String message) {
    return Error.builder().cause(cause).message(message).build();
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleUnexpectedTypeException(UnexpectedTypeException ex) {
    log.error("UnexpectedTypeException: ", ex);
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  @ExceptionHandler({UsernameNotFoundException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleNotFoundException(UsernameNotFoundException ex) {
    log.error("UsernameNotFoundException: ", ex);
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public List<Error> handleValidationException(MethodArgumentNotValidException ex) {
    log.error("MethodArgumentNotValidException: ", ex);
    // Extract the error message from the first validation error and return the Error.class in the response
    List<Error> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(
          Error.builder().cause(error.getField()).message(error.getDefaultMessage()).build());
    }
    return errors;
  }

  @ExceptionHandler(SignatureException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Error handleSignatureException(SignatureException ex) {
    log.error("SignatureException: ", ex);
    // Extract the error message from the first validation error and return the Error.class in the response
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  @ExceptionHandler(JsonProcessingException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleJsonProcessingException(JsonProcessingException ex) {
    log.debug("JsonProcessingException: ", ex);
    // Extract the error message from the first validation error and return the Error.class in the response
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Error handleIllegalArgumentException(IllegalArgumentException ex) {
    log.error("IllegalArgumentException: ", ex);
    // Extract the error message from the first validation error and return the Error.class in the response
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Error handleExpiredJwtException(ExpiredJwtException ex) {
    log.error("ExpiredJwtException: ", ex);
    // Extract the error message from the first validation error and return the Error.class in the response
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleNoSuchElementException(NoSuchElementException ex) {
    log.error("NoSuchElementException: ", ex);
    // Extract the error message from the first validation error and return the Error.class in the response
    if (ex.getCause() == null) {
      return buildError(UNKNOWN, ex.getMessage());
    }
    return buildError(ex.getCause().toString(), ex.getMessage());
  }


}

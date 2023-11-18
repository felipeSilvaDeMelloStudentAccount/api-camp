package api.camp.controller;


import api.camp.model.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.validation.UnexpectedTypeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExceptionHandlerAdvice {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleGlobalException(Exception ex) {
        log.error("Exception: ", ex);
        if (ex.getCause() == null) {
            return Error.builder().cause("Unknown").message(ex.getMessage()).build();
        }
        return Error.builder().cause(ex.getCause().toString()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleUnexpectedTypeException(UnexpectedTypeException ex) {
        log.error("UnexpectedTypeException: ", ex);
        return Error.builder().cause(ex.getCause().toString()).message(ex.getMessage()).build();
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(UsernameNotFoundException ex) {
        log.error("UsernameNotFoundException: ", ex);
        return Error.builder().cause(ex.getCause().toString()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleValidationException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: ", ex);
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
        log.error("SignatureException: ", ex);
        // Extract the error message from the first validation error and return the Error.class in the response
        return Error.builder()
                .cause(ex.getCause().toString())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handleJsonProcessingException(JsonProcessingException ex) {
        log.debug("JsonProcessingException: ", ex);
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
        log.error("IllegalArgumentException: ", ex);
        // Extract the error message from the first validation error and return the Error.class in the response
        return Error.builder()
                .cause(ex.getCause().toString())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Error handleExpiredJwtException(ExpiredJwtException ex) {
        log.error("ExpiredJwtException: ", ex);
        // Extract the error message from the first validation error and return the Error.class in the response
        return Error.builder()
                .cause(ex.getCause().toString())
                .message(ex.getMessage())
                .build();
    }
}

package br.dev.detowhey.urlshortner.exception.handler;

import br.dev.detowhey.urlshortner.exception.NotFoundException;
import br.dev.detowhey.urlshortner.exception.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Log4j2
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotFoundException e, HttpServletRequest request) {
        return createErrorResponse("Not Found URL", HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> dataBaseConnectionError(NotFoundException e, HttpServletRequest request) {
        return createErrorResponse("Database connection", HttpStatus.INTERNAL_SERVER_ERROR, e, request);
    }

    private ResponseEntity<StandardError> createErrorResponse(String error, HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, e.getMessage(), request.getRequestURI());
        log.error(error, e);
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}

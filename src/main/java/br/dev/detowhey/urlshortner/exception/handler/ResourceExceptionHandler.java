package br.dev.detowhey.urlshortner.exception.handler;

import br.dev.detowhey.urlshortner.exception.DataBaseConnectionException;
import br.dev.detowhey.urlshortner.exception.NotFoundException;
import br.dev.detowhey.urlshortner.exception.error.ErrorObject;
import br.dev.detowhey.urlshortner.exception.error.ErrorResponse;
import br.dev.detowhey.urlshortner.exception.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotFoundException e, HttpServletRequest request) {
        return createErrorResponse("Not Found URL", HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(DataBaseConnectionException.class)
    public ResponseEntity<StandardError> dataBaseConnectionError(NotFoundException e, HttpServletRequest request) {
        return createErrorResponse("Database connection", HttpStatus.INTERNAL_SERVER_ERROR, e, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorObject> errorObjects = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errorObjects);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<StandardError> createErrorResponse(String error, HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, e.getMessage(), request.getRequestURI());
        log.error(error, e);
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorObject(
                        error.getDefaultMessage(),
                        error.getField(),
                        error.getRejectedValue()
                )).toList();
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatusCode status, List<ErrorObject> errors) {
        return new ErrorResponse(
                Instant.now(),
                status.value(),
                "Request has invalid fields",
                ex.getBindingResult().getObjectName(),
                errors
        );
    }
}

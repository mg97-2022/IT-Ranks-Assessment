package com.it_ranks.employee.exception;

import com.it_ranks.employee.enums.ExceptionStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ExceptionUtils exceptionUtils;

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNoResourceFoundException(
            NoResourceFoundException ex, WebRequest webRequest) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(webRequest.getDescription(false) + " resource not found.")
                                    .status(ExceptionStatus.FAIL.toString()).error(statusCode.getReasonPhrase())
                                    .path(ex.getStackTrace()[0].toString()).exception(ex.toString())
                                    .cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    // Handles validation errors messages for annotations (@Valid)
    // For the response message, it will be an array of messages
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });

        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(errorMessages).status(ExceptionStatus.FAIL.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    // Handles validation errors messages for annotations such requestParams (@Validated)
    // For the response message, it will be an array of messages
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleConstraintViolationException(ConstraintViolationException ex) {

        String[] messagesArray = ex.getMessage().split(", ");
        List<String> errorMessages = new ArrayList<>(Arrays.asList(messagesArray));

        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(errorMessages).status(ExceptionStatus.FAIL.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    // Handles duplicate violation constraints that are thrown from db
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        String errorMessage = exceptionUtils.getDataIntegrityViolationMessage(ex.getMostSpecificCause().getMessage());
        HttpStatus statusCode = HttpStatus.CONFLICT;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(errorMessage).status(ExceptionStatus.FAIL.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<ExceptionResponseDTO> handleJpaObjectRetrievalFailureException(
            JpaObjectRetrievalFailureException ex) {
        String errorMessage = exceptionUtils.getJpaObjectRetrievalFailureExceptionMessage(ex.getMessage());
        HttpStatus statusCode = HttpStatus.NOT_FOUND;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(errorMessage).status(ExceptionStatus.FAIL.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCustomException(CustomException ex) {
        HttpStatus statusCode = ex.getStatusCode();

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(ex.getMessage()).status(ex.getStatus())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(ex.getMessage()).status(ExceptionStatus.ERROR.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).cause(ex.getCause()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }
}
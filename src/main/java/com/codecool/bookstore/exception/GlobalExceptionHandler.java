package com.codecool.bookstore.exception;

import com.codecool.bookstore.error.ResponseError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintDeclarationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintDeclarationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(ConstraintDeclarationException ex) {
        ResponseError error = new ResponseError("constraint_violation", "Constraint Violation");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ResponseError error = new ResponseError("data_integrity_violation",
                                              "Wrong data or already exists");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseError error = new ResponseError("illegal_argument", "Wrong data. Impossible to proceed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ResponseError> handleIllegalAccessException(IllegalAccessException ex) {
        ResponseError error = new ResponseError("illegal_access",
                                             "You don't have access to this resource");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

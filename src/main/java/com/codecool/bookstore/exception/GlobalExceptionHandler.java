package com.codecool.bookstore.exception;

import com.codecool.bookstore.error.ResponseError;
import com.codecool.bookstore.logger.LogService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintDeclarationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger;

    public GlobalExceptionHandler(LogService logService) {
        this.logger = logService.getLogger();
    }

    @ExceptionHandler(ConstraintDeclarationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(ConstraintDeclarationException ex) {
        logger.error("ConstraintDeclarationException thrown");
        ResponseError error = new ResponseError("constraint_violation", "Constraint Violation");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        logger.error("DataIntegrityViolationException thrown");
        ResponseError error = new ResponseError("data_integrity_violation",
                                              "Wrong data or already exists");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException thrown");
        ResponseError error = new ResponseError("illegal_argument", "Wrong data. Impossible to proceed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ResponseError> handleIllegalAccessException(IllegalAccessException ex) {
        logger.error("IllegalAccessException thrown");
        ResponseError error = new ResponseError("illegal_access",
                                             "You don't have access to this resource");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseError> handleNullPointerException(NullPointerException ex) {
        logger.error("NullPointerException thrown");
        ResponseError error = new ResponseError("resource_not_exists",
                "There is no such resource");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

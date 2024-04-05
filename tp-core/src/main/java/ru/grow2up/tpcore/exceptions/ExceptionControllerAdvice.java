package ru.grow2up.tpcore.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        ResponseError err = new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleExceptionConfirmationUserMail(ExceptionConfirmationUserMail e) {
        log.error(e.getMessage());
        ResponseError err = new ResponseError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleExceptionYandexCloudBroken(ExceptionYandexCloudBroken e) {
        log.error(e.getMessage());
        ResponseError err = new ResponseError(HttpStatus.FORBIDDEN.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleExceptionRedisBroken(ExceptionRedisBroken e) {
        log.error(e.getMessage());
        ResponseError err = new ResponseError(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleExceptionFileService(ExceptionFileService e) {
        log.error(e.getMessage());
        ResponseError err = new ResponseError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}

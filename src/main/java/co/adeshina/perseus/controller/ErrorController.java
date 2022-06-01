package co.adeshina.perseus.controller;

import co.adeshina.perseus.exception.InvalidEntityIdException;
import co.adeshina.perseus.model.dto.response.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
    private static final String ERROR_LOG_MSG = "Error object returned in response";

    @ExceptionHandler(InvalidEntityIdException.class)
    public ResponseEntity<ErrorDto> noEntity(InvalidEntityIdException e) {
        LOGGER.error(ERROR_LOG_MSG, e);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setCode(1);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}

package com.igorbavand.vendasapi.modulos.comum.handler;

import com.igorbavand.vendasapi.modulos.comum.exception.BadRequestException;
import com.igorbavand.vendasapi.modulos.comum.exception.ExceptionResponse;
import com.igorbavand.vendasapi.modulos.comum.exception.NotFoundException;
import java.lang.reflect.Method;
import javax.naming.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler
        implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("Erro async: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(String.format("O campo %s %s", ex.getFieldError().getField(),
                        ex.getFieldError().getDefaultMessage()), ex.getFieldError().toString());

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequest(
            BadRequestException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerNotFound(
            NotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ServiceUnavailableException.class)
    public final ResponseEntity<ExceptionResponse> handlerServiceUnavailable(
            ServiceUnavailableException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handlerMaxUploadSizeExceeded(
            MaxUploadSizeExceededException exception, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        "Max size files: 200MB", request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllException(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package br.com.api.f1.exception;

import br.com.api.f1.endpoints.DriverController;
import br.com.api.f1.exception.entity.ErrorCode;
import br.com.api.f1.exception.entity.ErrorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DriverController.class);

    private static final String EXCEPTION = "exception";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(final BusinessException exception, final WebRequest request) {
        return buildResponse(exception, HttpStatus.UNPROCESSABLE_ENTITY, ErrorCode.BUSINESS_ERROR, request);
    }

    private ResponseEntity<Object> buildResponse(
        final Exception exception, final HttpStatus httpStatus, final ErrorCode errorCode, final WebRequest request
    ) {
        final ErrorData response = new ErrorData(errorCode.toString(), exception.getMessage());

        final String errorMessage = MessageFormat.format("Exception: {0}", response);
        log.error(errorMessage, EXCEPTION, exception);

        return handleExceptionInternal(exception, response, new HttpHeaders(), httpStatus, request);
    }
}

package com.facens.troca.online.api.exceptionhandler;

import com.facens.troca.online.api.exceptionhandler.exceptions.UniqueEmailException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    private List<SimpleErrorOutput> createErrorList(BindingResult bindingResult) {
        List<SimpleErrorOutput> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String devpMessage = fieldError.toString();

            errors.add(new SimpleErrorOutput(userMessage, devpMessage));
        }

        return errors;
    }

    @ExceptionHandler({ UniqueEmailException.class } )
    public ResponseEntity<Object> handleUniqueEmailException(UniqueEmailException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("database.used-email", null, LocaleContextHolder.getLocale());
        String devpMessage = ExceptionUtils.getRootCauseMessage(ex);

        List<SimpleErrorOutput> errors = Arrays.asList(new SimpleErrorOutput(userMessage, devpMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
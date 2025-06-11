package com.cathay.exchangeflow.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.application.exception.DataNotFoundException;
import com.cathay.exchangeflow.core.MessageCode;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void testHandleApplicationException() {
        MessageCode code1 = MessageCode.of("error.code.1");
        MessageCode code2 = MessageCode.of("error.code.2");
        ApplicationException ex = new ApplicationException(List.of(code1, code2));
        ResponseEntity<Map<String, Object>> response = handler.handleApplicationException(ex);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertTrue(response.getBody().get("errors") instanceof List<?>);
        List<?> errors = (List<?>) response.getBody().get("errors");
        assertTrue(errors.contains("error.code.1"));
        assertTrue(errors.contains("error.code.2"));
    }


    @Test
    void testHandleHttpRequestMethodNotSupportedException() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");
        ResponseEntity<Map<String, Object>> response =
                handler.handleHttpRequestMethodNotSupportedException(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        List<?> errors = (List<?>) response.getBody().get("errors");
        assertTrue(errors.get(0).toString().contains("POST"));
    }

    @Test
    void testHandleBindException() {
        BindException ex = new BindException(new Object(), "objectName");
        ex.addError(new FieldError("objectName", "field2", "must not be null"));

        ResponseEntity<Map<String, Object>> response = handler.handleBindException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        List<?> errors = (List<?>) response.getBody().get("errors");
        assertTrue(errors.get(0).toString().contains("field2"));
    }

    @Test
    void testHandleNoHandlerFoundException() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/notfound", null);
        ResponseEntity<Map<String, Object>> response = handler.handleNoHandlerFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        List<?> errors = (List<?>) response.getBody().get("errors");
        assertTrue(errors.get(0).toString().contains("/notfound"));
    }

    @Test
    void testHandleDataNotFoundException() {
        DataNotFoundException ex = new DataNotFoundException();
        ResponseEntity<?> response = handler.handleDataNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}

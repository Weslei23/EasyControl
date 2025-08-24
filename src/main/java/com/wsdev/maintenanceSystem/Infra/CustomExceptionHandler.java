package com.wsdev.maintenanceSystem.Infra;

import com.wsdev.maintenanceSystem.Exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler( CustomerNotFoundException.class )
    private ResponseEntity<String> customerNotFoundException( CustomerNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Customer not found" );
    }
}

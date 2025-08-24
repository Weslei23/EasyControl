package com.wsdev.maintenanceSystem.Infra;

import com.wsdev.maintenanceSystem.Exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler( EmployeeNotFoundException.class )
    private ResponseEntity<String> employeeNotFoundException( EmployeeNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Employee not found" );
    }
}

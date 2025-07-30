package com.wsdev.maintenanceSystem.Infra;

import com.wsdev.maintenanceSystem.Exception.CustomerNotFoundException;
import com.wsdev.maintenanceSystem.Exception.EmployeeNotFoundException;
import com.wsdev.maintenanceSystem.Exception.MaintenanceNotFoundException;
import com.wsdev.maintenanceSystem.Exception.UserNotFoundException;
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

    @ExceptionHandler( EmployeeNotFoundException.class )
    private ResponseEntity<String> employeeNotFoundException( EmployeeNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Employee not found" );
    }

    @ExceptionHandler( MaintenanceNotFoundException.class )
    private ResponseEntity<String> maintenanceNotFoundException( MaintenanceNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Maintenance not found" );
    }

    @ExceptionHandler( UserNotFoundException.class )
    private ResponseEntity<String> userNotFoundException( UserNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "User not found" );
    }
}

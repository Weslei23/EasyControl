package com.wsdev.maintenanceSystem.Infra;

import com.wsdev.maintenanceSystem.Exception.MaintenanceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MaintenanceExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler( MaintenanceNotFoundException.class )
    private ResponseEntity<String> maintenanceNotFoundException( MaintenanceNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Maintenance not found" );
    }
}

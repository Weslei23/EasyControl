package com.wsdev.maintenanceSystem.Infra;

import com.wsdev.maintenanceSystem.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler( UserNotFoundException.class )
    private ResponseEntity<String> userNotFoundException( UserNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "User not found" );
    }
}

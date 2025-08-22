package com.wsdev.maintenanceSystem.Exception;

public class UserAlreadyRegisteredException extends RuntimeException
{
    public UserAlreadyRegisteredException()
    {
        super( "User is already registered" );
    }

    public UserAlreadyRegisteredException( String message )
    {
        super( message );
    }
}

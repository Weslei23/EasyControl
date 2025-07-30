package com.wsdev.maintenanceSystem.Exception;

public class CustomerNotFoundException extends RuntimeException
{
    public CustomerNotFoundException()
    {
        super( "Customer not found" );
    }

    public CustomerNotFoundException( String message )
    {
        super( message );
    }
}

package com.wsdev.maintenanceSystem.Exception;

public class IllegalArgumentException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public IllegalArgumentException( Long id )
    {
        super( "Customer not found for id " + id );
    }
}

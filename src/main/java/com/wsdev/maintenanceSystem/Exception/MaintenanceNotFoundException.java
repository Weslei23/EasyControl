package com.wsdev.maintenanceSystem.Exception;

public class MaintenanceNotFoundException extends RuntimeException
{
    public MaintenanceNotFoundException()
    {
        super( "Maintenance not found" );
    }

    public MaintenanceNotFoundException( String message )
    {
        super( message );
    }
}

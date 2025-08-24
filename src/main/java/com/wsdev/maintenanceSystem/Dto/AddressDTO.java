package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Database.Models.AddressModel;

public record AddressDTO( String street, String number, String complement, String neighborhood, String city, String state, String postalCode )
{
    public static AddressDTO from( AddressModel addressModel )
    {
        return new AddressDTO(
        addressModel.getStreet(),
        addressModel.getNumber(),
        addressModel.getComplement(),
        addressModel.getNeighborhood(),
        addressModel.getCity(),
        addressModel.getState(),
        addressModel.getPostalCode() );
    }

    public AddressModel toEntity()
    {
        AddressModel addressModel = new AddressModel();

        addressModel.setStreet( street );
        addressModel.setNumber( number );
        addressModel.setComplement( complement );
        addressModel.setNeighborhood( neighborhood );
        addressModel.setCity( city );
        addressModel.setState( state );
        addressModel.setPostalCode( postalCode );

        return addressModel;
    }
}

package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Models.CustomerModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record CustomerRequestDTO(UUID id, String name, String telephone, String email, AddressDTO address, List<MaintenanceDTO> maintenances )
{
    public CustomerModel toEntity()
    {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId( id );
        customerModel.setName( name );
        customerModel.setTelephone( telephone );
        customerModel.setEmail( email );
        customerModel.setAddress( Optional.ofNullable( address )
            .map( AddressDTO::toEntity ).orElse( null ) );
        customerModel.setMaintenances( Optional.ofNullable( maintenances )
            .orElse( List.of() ).stream().map( MaintenanceDTO::toEntity ).toList()
        );

        return customerModel;
    }
}
package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Models.CustomerModel;

import java.util.List;
import java.util.Optional;

public record CustomerDTO(
        Long id,
        String name,
        String telephone,
        String email,
        AddressDTO address,
        List<MaintenanceDTO> maintenances )
{
    public static CustomerDTO from( CustomerModel customerModel )
    {
        return new CustomerDTO(
        customerModel.getId(),
        customerModel.getName(),
        customerModel.getTelephone(),
        customerModel.getEmail(),
        AddressDTO.from( customerModel.getAddress() ),
        Optional.ofNullable( customerModel.getMaintenances() )
                .orElse( List.of() ).stream().map( MaintenanceDTO::from ).toList() );
    }

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
            .orElse( List.of() ).stream().map( MaintenanceDTO::toEntity ).toList() );

        return customerModel;
    }
}

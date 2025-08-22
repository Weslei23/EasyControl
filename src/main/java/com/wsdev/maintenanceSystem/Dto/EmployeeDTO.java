package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Models.EmployeeModel;

import java.util.List;
import java.util.Optional;

public record EmployeeDTO(
        Long id,
        String name,
        String telephone,
        String specialty,
        List<MaintenanceDTO> maintenances )
{
    public static EmployeeDTO from( EmployeeModel employeeModel )
    {
        return new EmployeeDTO(
        employeeModel.getId(),
        employeeModel.getName(),
        employeeModel.getTelephone(),
        employeeModel.getSpecialty(),
        Optional.ofNullable( employeeModel.getMaintenances() )
            .orElse( List.of() ).stream().map( MaintenanceDTO::from ).toList() );
    }

    public EmployeeModel toEntity()
    {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId( id );
        employeeModel.setName( name );
        employeeModel.setTelephone( telephone );
        employeeModel.setSpecialty( specialty );
        employeeModel.setMaintenances( Optional.ofNullable( maintenances )
            .orElse( List.of() ).stream().map( MaintenanceDTO::toEntity ).toList() );

        return employeeModel;
    }
}

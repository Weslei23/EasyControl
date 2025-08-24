package com.wsdev.maintenanceSystem.Dto;

import com.wsdev.maintenanceSystem.Database.Models.EmployeeModel;
import java.util.List;
import java.util.Optional;

public record EmployeeRequestDTO(
        Long id,
        String name,
        String telephone,
        String specialty,
        List<MaintenanceDTO> maintenances )
{
    public EmployeeModel toEntity()
    {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId( id );
        employeeModel.setName( name );
        employeeModel.setTelephone( telephone );
        employeeModel.setSpecialty( specialty );
        employeeModel.setMaintenances( Optional.ofNullable( maintenances )
            .orElse( List.of() ).stream().map( MaintenanceDTO::toEntity ).toList()
        );

        return employeeModel;
    }
}

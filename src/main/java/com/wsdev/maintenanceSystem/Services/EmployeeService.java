package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.EmployeeDTO;
import com.wsdev.maintenanceSystem.Dto.EmployeeRequestDTO;
import com.wsdev.maintenanceSystem.Models.EmployeeModel;
import com.wsdev.maintenanceSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getEmployees()
    {
        return employeeRepository.findAll().stream()
            .map( EmployeeDTO::from ).toList();
    }

    public EmployeeDTO getEmployeeById( UUID id )
    {
        EmployeeModel employeeModel = employeeRepository.findById( id )
            .orElseThrow( () -> new RuntimeException( "Funcionário não encontrado" ) );
        return EmployeeDTO.from( employeeModel );
    }

    public EmployeeDTO addEmployee( EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeModel employeeModel = employeeRepository.save( employeeRequestDTO.toEntity() );
        return EmployeeDTO.from( employeeModel );
    }

    public EmployeeDTO updateEmployee( UUID id, EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeModel employeeModel = employeeRepository.findById( id )
            .orElseThrow( () -> new RuntimeException( "Funcionário não encontrado" ) );

        employeeModel.setName( employeeRequestDTO.name() );
        employeeModel.setTelephone( employeeRequestDTO.telephone() );
        employeeModel.setSpecialty( employeeRequestDTO.specialty() );
//        employeeModel.setMaintenances( employeeRequestDTO.maintenances().stream().map( MaintenanceDTO::toEntity).toList() );

        EmployeeModel employeeModelUpdate = employeeRepository.save( employeeModel );

        return EmployeeDTO.from( employeeModelUpdate );
    }

    public void deleteEmployeeById( UUID id )
    {
        employeeRepository.deleteById( id );
    }
}

package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.EmployeeDTO;
import com.wsdev.maintenanceSystem.Dto.EmployeeRequestDTO;
import com.wsdev.maintenanceSystem.Exception.EmployeeNotFoundException;
import com.wsdev.maintenanceSystem.Database.Models.EmployeeModel;
import com.wsdev.maintenanceSystem.Database.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public EmployeeDTO getEmployeeByName( String name )
    {
        EmployeeModel employeeModel = employeeRepository.findByName( name )
                .orElseThrow( EmployeeNotFoundException::new );
        return EmployeeDTO.from( employeeModel );
    }

    public EmployeeDTO getEmployeeById( Long id )
    {
        EmployeeModel employeeModel = employeeRepository.findById( id )
            .orElseThrow( EmployeeNotFoundException::new );
        return EmployeeDTO.from( employeeModel );
    }

    public EmployeeDTO addEmployee( EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeModel employeeModel = employeeRepository.save( employeeRequestDTO.toEntity() );
        return EmployeeDTO.from( employeeModel );
    }

    public EmployeeDTO updateEmployee( Long id, EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeModel employeeModel = employeeRepository.findById( id )
            .orElseThrow( EmployeeNotFoundException::new );

        employeeModel.setName( employeeRequestDTO.name() );
        employeeModel.setTelephone( employeeRequestDTO.telephone() );
        employeeModel.setSpecialty( employeeRequestDTO.specialty() );
        EmployeeModel employeeModelUpdate = employeeRepository.save( employeeModel );

        return EmployeeDTO.from( employeeModelUpdate );
    }

    public void deleteEmployeeById( Long id )
    {
        employeeRepository.deleteById( id );
    }
}

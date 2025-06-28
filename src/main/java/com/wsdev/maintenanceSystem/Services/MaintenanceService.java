package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import com.wsdev.maintenanceSystem.Dto.MaintenanceRequestDTO;
import com.wsdev.maintenanceSystem.Models.*;
import com.wsdev.maintenanceSystem.Repository.CustomerRepository;
import com.wsdev.maintenanceSystem.Repository.EmployeeRepository;
import com.wsdev.maintenanceSystem.Repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceService
{
    @Autowired private MaintenanceRepository maintenanceRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private EmployeeRepository employeeRepository;

    public List<MaintenanceDTO> getMaintenances()
    {
        return maintenanceRepository.findAll()
            .stream().map( MaintenanceDTO::from ).toList();
    }

    public MaintenanceDTO getMaintenanceById( Long id )
    {
        MaintenanceModel maintenance = maintenanceRepository.findById( id )
            .orElseThrow( () -> new RuntimeException( "Manutenção não encontrada" ) );
        return MaintenanceDTO.from( maintenance );
    }

    public MaintenanceDTO addMaintenance( MaintenanceRequestDTO dto )
    {
        CustomerModel customer = customerRepository.findById( dto.customerId() )
            .orElseThrow( () -> new RuntimeException( "Cliente não encontrado") );

        EmployeeModel employee = employeeRepository.findById( dto.employeeId() )
            .orElseThrow( () -> new RuntimeException( "Funcionário não encontrado" ) );

        MaintenanceModel maintenance = new MaintenanceModel();
        populateFromDTO( maintenance, dto, customer, employee );

        maintenance = maintenanceRepository.save( maintenance );
        return MaintenanceDTO.from( maintenance );
    }

    public MaintenanceDTO updateMaintenance( Long id, MaintenanceRequestDTO dto )
    {
        MaintenanceModel maintenance = maintenanceRepository.findById( id )
            .orElseThrow( () -> new RuntimeException( "Manutenção não encontrada" ) );

        CustomerModel customer = customerRepository.findById( dto.customerId() )
            .orElseThrow( () -> new RuntimeException( "Cliente não encontrado" ) );

        EmployeeModel employee = employeeRepository.findById( dto.employeeId() )
            .orElseThrow( () -> new RuntimeException( "Funcionário não encontrado" ) );

        populateFromDTO( maintenance, dto, customer, employee );

        maintenance = maintenanceRepository.save( maintenance );
        return MaintenanceDTO.from( maintenance );
    }

    public void deleteMaintenance( Long id )
    {
        if ( !maintenanceRepository.existsById( id ) )
        {
            throw new RuntimeException( "Manutenção não encontrada" );
        }
        maintenanceRepository.deleteById( id );
    }

    private void populateFromDTO( MaintenanceModel model, MaintenanceRequestDTO dto, CustomerModel customer, EmployeeModel employee )
    {
        model.setScheduledDate( dto.scheduledDate() );
        model.setDescriptionService( dto.descriptionService() );
        model.setStatus(  dto.statusModel() );
        model.setTypeMaterial( dto.typeMaterialModel() );
        model.setCustomer( customer );
        model.setEmployee( employee );
    }
}
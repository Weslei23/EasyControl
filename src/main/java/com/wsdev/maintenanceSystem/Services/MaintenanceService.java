package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Database.Models.CustomerModel;
import com.wsdev.maintenanceSystem.Database.Models.EmployeeModel;
import com.wsdev.maintenanceSystem.Database.Models.MaintenanceModel;
import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import com.wsdev.maintenanceSystem.Dto.MaintenanceRequestDTO;
import com.wsdev.maintenanceSystem.Exception.CustomerNotFoundException;
import com.wsdev.maintenanceSystem.Exception.EmployeeNotFoundException;
import com.wsdev.maintenanceSystem.Exception.MaintenanceNotFoundException;
import com.wsdev.maintenanceSystem.Database.Repository.CustomerRepository;
import com.wsdev.maintenanceSystem.Database.Repository.EmployeeRepository;
import com.wsdev.maintenanceSystem.Database.Repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceService
{
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<MaintenanceDTO> getMaintenances()
    {
        return maintenanceRepository.findAll()
            .stream().map( MaintenanceDTO::from ).toList();
    }

    public MaintenanceDTO getMaintenanceById( Long id )
    {
        MaintenanceModel maintenance = maintenanceRepository.findById( id )
            .orElseThrow( MaintenanceNotFoundException::new );
        return MaintenanceDTO.from( maintenance );
    }

    public MaintenanceDTO addMaintenance( MaintenanceRequestDTO dto )
    {
        CustomerModel customer = customerRepository.findById( dto.customerId() )
            .orElseThrow( CustomerNotFoundException::new );

        EmployeeModel employee = employeeRepository.findById( dto.employeeId() )
            .orElseThrow( EmployeeNotFoundException::new );

        MaintenanceModel maintenance = new MaintenanceModel();
        populateFromDTO( maintenance, dto, customer, employee );

        maintenance = maintenanceRepository.save( maintenance );
        return MaintenanceDTO.from( maintenance );
    }

    public MaintenanceDTO updateMaintenance( Long id, MaintenanceRequestDTO dto )
    {
        MaintenanceModel maintenance = maintenanceRepository.findById( id )
            .orElseThrow( MaintenanceNotFoundException::new );

        CustomerModel customer = customerRepository.findById( dto.customerId() )
            .orElseThrow( CustomerNotFoundException::new );

        EmployeeModel employee = employeeRepository.findById( dto.employeeId() )
            .orElseThrow( EmployeeNotFoundException::new );

        populateFromDTO( maintenance, dto, customer, employee );

        maintenance = maintenanceRepository.save( maintenance );
        return MaintenanceDTO.from( maintenance );
    }

    public void deleteMaintenance( Long id )
    {
        if ( !maintenanceRepository.existsById( id ) )
        {
            throw new MaintenanceNotFoundException();
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
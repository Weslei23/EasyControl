package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.CustomerDTO;
import com.wsdev.maintenanceSystem.Dto.CustomerRequestDTO;
import com.wsdev.maintenanceSystem.Exception.CustomerExistsException;
import com.wsdev.maintenanceSystem.Exception.CustomerNotFoundException;
import com.wsdev.maintenanceSystem.Database.Models.CustomerModel;
import com.wsdev.maintenanceSystem.Database.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getCustomers()
    {
        return customerRepository.findAll().stream()
            .map( CustomerDTO::from ).toList();
    }

    public CustomerDTO getCustomerByName( String name )
    {
        CustomerModel customerModel = customerRepository.findByName( name )
                .orElseThrow( CustomerNotFoundException::new );
        return CustomerDTO.from( customerModel );
    }

    public CustomerDTO getCustomerByEmail( String email )
    {
        CustomerModel customerModel = customerRepository.findByEmail( email )
                .orElseThrow( CustomerNotFoundException::new );
        return CustomerDTO.from( customerModel );
    }

    public CustomerDTO getCustomerById( Long id )
    {
        CustomerModel customerModel = customerRepository.findById( id )
            .orElseThrow( CustomerNotFoundException::new );
        return CustomerDTO.from( customerModel );
    }

    public CustomerDTO addCustomer( CustomerRequestDTO customerRequestDTO )
    {
        if( customerRepository.existsByEmail( customerRequestDTO.email() ) )
        {
            throw new CustomerExistsException( "Customer already exists with this email" );
        }

        CustomerModel customerModel = customerRepository.save( customerRequestDTO.toEntity() );
        return CustomerDTO.from( customerModel );
    }

    public CustomerDTO updateCustomer( Long id, CustomerRequestDTO customerRequestDTO )
    {
        CustomerModel customerModel = customerRepository.findById( id )
            .orElseThrow( CustomerNotFoundException::new );

        customerModel.setName( customerRequestDTO.name() );
        customerModel.setTelephone( customerRequestDTO.telephone() );
        customerModel.setEmail( customerRequestDTO.email() );
        customerModel.setAddress( customerRequestDTO.address().toEntity() );
        CustomerModel customerModelUpdate = customerRepository.save( customerModel );

        return CustomerDTO.from( customerModelUpdate );
    }

    public void deleteCustomerById( Long id )
    {
        customerRepository.deleteById( id );
    }
}


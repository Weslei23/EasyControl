package com.wsdev.maintenanceSystem.Services;

import com.wsdev.maintenanceSystem.Dto.CustomerDTO;
import com.wsdev.maintenanceSystem.Dto.CustomerRequestDTO;
import com.wsdev.maintenanceSystem.Models.CustomerModel;
import com.wsdev.maintenanceSystem.Repository.CustomerRepository;
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

    public CustomerDTO getCustomerById( Long id )
    {
        CustomerModel customerModel = customerRepository.findById( id )
            .orElseThrow( () -> new RuntimeException( "Cliente não encontrado" ) );
        return CustomerDTO.from( customerModel );
    }

    public CustomerDTO addCustomer( CustomerRequestDTO customerRequestDTO )
    {
        CustomerModel customerModel = customerRepository.save( customerRequestDTO.toEntity() );
        return CustomerDTO.from( customerModel );
    }

    public CustomerDTO updateCustomer( Long id, CustomerRequestDTO customerRequestDTO )
    {
        CustomerModel customerModel = customerRepository.findById( id )
            .orElseThrow( () -> new RuntimeException( "Cliente não encontrado") );

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


package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.CustomerDTO;
import com.wsdev.maintenanceSystem.Dto.CustomerRequestDTO;
import com.wsdev.maintenanceSystem.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping( "/api/v1/customers" )
@CrossOrigin( origins = "http://localhost:5173" )
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping( )
    public ResponseEntity<List<CustomerDTO>> getCustomers()
    {
        return ResponseEntity.ok( customerService.getCustomers() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<CustomerDTO> getCustomerById( @PathVariable Long id )
    {
        return ResponseEntity.ok( customerService.getCustomerById( id ) );
    }

    @PostMapping( "/add" )
    public ResponseEntity<CustomerDTO> addCustomer( @RequestBody @Valid CustomerRequestDTO customerRequestDTO )
    {
        CustomerDTO customerDTO = customerService.addCustomer( customerRequestDTO );
        return new ResponseEntity<>( customerDTO, HttpStatus.CREATED );
    }

    @PutMapping("/update/{id}" )
    public ResponseEntity<CustomerDTO> updateCustomer( @PathVariable Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO )
    {
        return ResponseEntity.ok( customerService.updateCustomer( id, customerRequestDTO ) );
    }

    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteCustomer( @PathVariable Long id )
    {
        customerService.deleteCustomerById( id );
        return ResponseEntity.noContent().build();
    }
}

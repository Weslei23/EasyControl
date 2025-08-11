package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.CustomerDTO;
import com.wsdev.maintenanceSystem.Dto.CustomerRequestDTO;
import com.wsdev.maintenanceSystem.Services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/customers" )
@CrossOrigin( origins = "http://localhost:5173" )
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @Operation( description = "Irá retornar uma lista de clientes. " )
    @GetMapping( )
    public ResponseEntity<List<CustomerDTO>> getCustomers()
    {
       try
       {
           return ResponseEntity.ok( customerService.getCustomers() );
       }
       catch ( Exception exception )
       {
           return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( null );
       }
    }

    @Operation( description = "Irá buscar um usuário pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<CustomerDTO> getCustomerById( @PathVariable Long id )
    {
        return ResponseEntity.ok( customerService.getCustomerById( id ) );
    }

    @Operation( description = "Irá buscar um cliente pelo 'Nome' informado." )
    @GetMapping( "/findByName/{name}" )
    public ResponseEntity<CustomerDTO> getCustomerByName( @PathVariable String name )
    {
        return ResponseEntity.ok( customerService.getCustomerByName( name ) );
    }

    @Operation( description = "Irá buscar um usuário pelo 'Email' informado." )
    @GetMapping( "/findByEmail/{email}" )
    public ResponseEntity<CustomerDTO> getCustomerByEmail( @PathVariable String email )
    {
         return ResponseEntity.ok( customerService.getCustomerByEmail( email ) );
    }

    @Operation( description = "Irá adicionar um cliente ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<CustomerDTO> addCustomer( @RequestBody @Valid CustomerRequestDTO customerRequestDTO )
    {
        CustomerDTO customerDTO = customerService.addCustomer( customerRequestDTO );
        return new ResponseEntity<>( customerDTO, HttpStatus.CREATED );
    }

    @Operation( description = "Irá atualizar um cliente a partir do 'ID' informado." )
    @PutMapping("/update/{id}" )
    public ResponseEntity<CustomerDTO> updateCustomer( @PathVariable Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO )
    {
        return ResponseEntity.ok( customerService.updateCustomer( id, customerRequestDTO ) );
    }

    @Operation( description = "Irá deletar um cliente a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteCustomer( @PathVariable Long id )
    {
        customerService.deleteCustomerById( id );
        return ResponseEntity.noContent().build();
    }
}

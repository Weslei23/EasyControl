package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.CustomerDTO;
import com.wsdev.maintenanceSystem.Dto.CustomerRequestDTO;
import com.wsdev.maintenanceSystem.Services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
       return ResponseEntity.ok( customerService.getCustomers() );
    }

    @Operation( description = "Irá buscar um usuário pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<CustomerDTO> getCustomerById( @PathVariable Long id )
    {
        try
        {
            return ResponseEntity.ok( customerService.getCustomerById( id ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá buscar um cliente pelo 'Nome' informado." )
    @GetMapping( "/findByName/{name}" )
    public ResponseEntity<CustomerDTO> getCustomerByName( @PathVariable String name )
    {
        try
        {
            return ResponseEntity.ok( customerService.getCustomerByName( name ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá buscar um usuário pelo 'Email' informado." )
    @GetMapping( "/findByEmail/{email}" )
    public ResponseEntity<CustomerDTO> getCustomerByEmail( @PathVariable String email )
    {
        try
        {
            return ResponseEntity.ok( customerService.getCustomerByEmail( email ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá adicionar um cliente ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<String> addCustomer( @RequestBody @Valid CustomerRequestDTO customerRequestDTO )
    {
        try
        {
            customerService.addCustomer( customerRequestDTO );
            return ResponseEntity.ok( "Customer successfully added." );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.badRequest().body( exception.getMessage() );
        }

    }

    @Operation( description = "Irá atualizar um cliente a partir do 'ID' informado." )
    @PutMapping("/update/{id}" )
    public ResponseEntity<String> updateCustomer( @PathVariable Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO )
    {
        try
        {
            customerService.updateCustomer( id, customerRequestDTO );
            return ResponseEntity.ok( "Customer successfully updated." );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá deletar um cliente a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteCustomer( @PathVariable Long id )
    {
        try
        {
            customerService.deleteCustomerById( id );
            return ResponseEntity.noContent().build();
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }
}

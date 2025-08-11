package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.EmployeeDTO;
import com.wsdev.maintenanceSystem.Dto.EmployeeRequestDTO;
import com.wsdev.maintenanceSystem.Services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/employees" )
@CrossOrigin( origins = "http://localhost:5173" )
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @Operation( description = "Irá retornar uma lista de funcionários." )
    @GetMapping( )
    public ResponseEntity<List<EmployeeDTO>> getEmployees()
    {
        try
        {
            return ResponseEntity.ok( employeeService.getEmployees() );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation( description = "Irá buscar um funcionário pelo 'Nome' informado." )
    @GetMapping( "/findByName/{name}" )
    public ResponseEntity<EmployeeDTO> getEmployeeByName( @PathVariable String name )
    {
        try
        {
            return ResponseEntity.ok( employeeService.getEmployeeByName( name ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá buscar um funcionário pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<EmployeeDTO> getEmployeeById( @PathVariable Long id )
    {
        try
        {
            return ResponseEntity.ok( employeeService.getEmployeeById( id ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá adicionar um cliente ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<String> addEmployee( @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        try
        {
            employeeService.addEmployee( employeeRequestDTO );
            return ResponseEntity.ok( "Employee successfully added." );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.badRequest().body( exception.getMessage() );
        }
    }

    @Operation( description = "Irá deletar um funcionário a partir do 'ID' informado." )
    @PutMapping( "/update/{id}" )
    public ResponseEntity<String> updateEmployee( @PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        try
        {
            employeeService.updateEmployee( id, employeeRequestDTO );
            return ResponseEntity.ok( "Employee successfully updated." );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá deletar um funcionário a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteEmployeeById( @PathVariable Long id )
    {
        try
        {
            employeeService.deleteEmployeeById( id );
            return ResponseEntity.noContent().build();
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.EmployeeDTO;
import com.wsdev.maintenanceSystem.Dto.EmployeeRequestDTO;
import com.wsdev.maintenanceSystem.Services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok( employeeService.getEmployees() );
    }

    @Operation( description = "Irá buscar um funcionário pelo 'Nome' informado." )
    @GetMapping( "/findByName/{name}" )
    public ResponseEntity<EmployeeDTO> getEmployeeByName( @PathVariable String name )
    {
        return ResponseEntity.ok( employeeService.getEmployeeByName( name ) );
    }

    @Operation( description = "Irá buscar um funcionário pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<EmployeeDTO> getEmployeeById( @PathVariable Long id )
    {
        return ResponseEntity.ok( employeeService.getEmployeeById( id ) );
    }

    @Operation( description = "Irá adicionar um cliente ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<EmployeeDTO> addEmployee( @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeDTO employeeDTO = employeeService.addEmployee( employeeRequestDTO );

        return new ResponseEntity<>( employeeDTO, HttpStatus.CREATED );
    }

    @Operation( description = "Irá deletar um funcionário a partir do 'ID' informado." )
    @PutMapping( "/update/{id}" )
    public ResponseEntity<EmployeeDTO> updateEmployee( @PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        return ResponseEntity.ok( employeeService.updateEmployee( id, employeeRequestDTO ) );
    }

    @Operation( description = "Irá deletar um funcionário a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteEmployeeById( @PathVariable Long id )
    {
            employeeService.deleteEmployeeById( id );
            return ResponseEntity.noContent().build();
    }
}

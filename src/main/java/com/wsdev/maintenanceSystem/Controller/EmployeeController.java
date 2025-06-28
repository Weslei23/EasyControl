package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.EmployeeDTO;
import com.wsdev.maintenanceSystem.Dto.EmployeeRequestDTO;
import com.wsdev.maintenanceSystem.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/api/v1/employees" )
@CrossOrigin( origins = "http://localhost:5173" )
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping( )
    public ResponseEntity<List<EmployeeDTO>> getEmployees()
    {
        return ResponseEntity.ok( employeeService.getEmployees() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<EmployeeDTO> getEmployeeById( @PathVariable Long id )
    {
        return ResponseEntity.ok( employeeService.getEmployeeById( id ) );
    }

    @PostMapping( "/add" )
    public ResponseEntity<EmployeeDTO> addEmployee( @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeDTO employeeDTO = employeeService.addEmployee( employeeRequestDTO );

        return new ResponseEntity<>( employeeDTO, HttpStatus.CREATED );
    }

    @PutMapping( "/update/{id}" )
    public ResponseEntity<EmployeeDTO> updateEmployee( @PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        return ResponseEntity.ok( employeeService.updateEmployee( id, employeeRequestDTO ) );
    }

    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteEmployeeById( @PathVariable Long id )
    {
            employeeService.deleteEmployeeById( id );
            return ResponseEntity.noContent().build();
    }
}

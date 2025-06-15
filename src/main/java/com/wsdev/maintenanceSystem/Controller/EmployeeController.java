package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.EmployeeDTO;
import com.wsdev.maintenanceSystem.Dto.EmployeeRequestDTO;
import com.wsdev.maintenanceSystem.Models.EmployeeModel;
import com.wsdev.maintenanceSystem.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/LineaMaint" )
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping( "/employees" )
    public ResponseEntity<List<EmployeeDTO>> getEmployeers()
    {
        return ResponseEntity.ok( employeeService.getEmployeers() );
    }

    @GetMapping( "/employee/{id}" )
    public ResponseEntity<EmployeeDTO> getEmployeeById( @PathVariable UUID id )
    {
        return ResponseEntity.ok( employeeService.getEmployeeById( id ) );
    }

    @PostMapping( "/employee/add" )
    public ResponseEntity<EmployeeDTO> addEmployee( @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        EmployeeDTO employeeDTO = employeeService.addEmployee( employeeRequestDTO );

        return new ResponseEntity<>( employeeDTO, HttpStatus.CREATED );
    }

    @PutMapping( "/employee/update/{id}" )
    public ResponseEntity<EmployeeDTO> updateEmployee( @PathVariable UUID id, @RequestBody EmployeeRequestDTO employeeRequestDTO )
    {
        return ResponseEntity.ok( employeeService.updateEmployee( id, employeeRequestDTO ) );
    }

    @DeleteMapping( "/employee/delete/{id}" )
    public ResponseEntity<Void> deleteEmployeeById( @PathVariable UUID id )
    {
            employeeService.deleteEmployeeById( id );
            return ResponseEntity.noContent().build();
    }
}

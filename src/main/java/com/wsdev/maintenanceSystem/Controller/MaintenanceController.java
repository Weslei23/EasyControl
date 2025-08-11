package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import com.wsdev.maintenanceSystem.Dto.MaintenanceRequestDTO;
import com.wsdev.maintenanceSystem.Services.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/maintenances" )
@CrossOrigin( origins = "http://localhost:5173" )
public class MaintenanceController
{
    @Autowired
    private MaintenanceService maintenanceService;

    @Operation( description = "Irá retornar uma lista de manutenções." )
    @GetMapping( )
    public ResponseEntity<List<MaintenanceDTO>> getMaintenances()
    {
        return ResponseEntity.ok( maintenanceService.getMaintenances() );
    }

    @Operation( description = "Irá buscar uma manutenção pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<MaintenanceDTO> getMaintenanceById( @PathVariable Long id )
    {
        try
        {
            return ResponseEntity.ok( maintenanceService.getMaintenanceById( id ) );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá adicionar uma manutenção ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<String> addMaintenance( @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        try
        {
            maintenanceService.addMaintenance( dto );
            return ResponseEntity.ok( "Maintenance succesfully added." );
        }
        catch( Exception exception )
        {
            return ResponseEntity.badRequest().body( exception.getMessage() );
        }
    }

    @Operation( description = "Irá atualizar uma manutenção a partir do 'ID' informado." )
    @PutMapping( "/update/{id}" )
    public ResponseEntity<String> updateMaintenance( @PathVariable Long id, @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        try
        {
            maintenanceService.updateMaintenance( id, dto );
            return ResponseEntity.ok( "Maintenance successfully updated" );
        }
        catch( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation( description = "Irá deletar uma manutenção a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteMaintenance( @PathVariable Long id )
    {
        try
        {
            maintenanceService.deleteMaintenance( id );
            return ResponseEntity.noContent().build();
        }
        catch( Exception exception )
        {
            return ResponseEntity.notFound().build();
        }
    }
}
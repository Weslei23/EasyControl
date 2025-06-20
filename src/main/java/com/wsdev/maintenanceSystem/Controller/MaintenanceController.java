package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import com.wsdev.maintenanceSystem.Dto.MaintenanceRequestDTO;
import com.wsdev.maintenanceSystem.Services.MaintenanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/api/v1/maintenances" )
@CrossOrigin( origins = "http://localhost:5173" )
public class MaintenanceController
{
    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping( )
    public ResponseEntity<List<MaintenanceDTO>> getMaintenances()
    {
        List<MaintenanceDTO> list = maintenanceService.getMaintenances();
        return ResponseEntity.ok( list );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<MaintenanceDTO> getMaintenanceById( @PathVariable UUID id )
    {
        MaintenanceDTO dto = maintenanceService.getMaintenanceById( id );
        return ResponseEntity.ok( dto );
    }

    @PostMapping( "/add" )
    public ResponseEntity<MaintenanceDTO> createMaintenance( @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        MaintenanceDTO created = maintenanceService.addMaintenance( dto );
        return ResponseEntity.ok( created );
    }

    @PutMapping( "/update/{id}" )
    public ResponseEntity<MaintenanceDTO> updateMaintenance( @PathVariable UUID id, @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        MaintenanceDTO updated = maintenanceService.updateMaintenance( id, dto );
        return ResponseEntity.ok( updated );
    }

    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteMaintenance( @PathVariable UUID id )
    {
        maintenanceService.deleteMaintenance( id );
        return ResponseEntity.noContent().build();
    }
}
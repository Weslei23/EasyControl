package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import com.wsdev.maintenanceSystem.Dto.MaintenanceRequestDTO;
import com.wsdev.maintenanceSystem.Services.MaintenanceService;
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

    @GetMapping( )
    public ResponseEntity<List<MaintenanceDTO>> getMaintenances()
    {
        List<MaintenanceDTO> list = maintenanceService.getMaintenances();
        return ResponseEntity.ok( list );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<MaintenanceDTO> getMaintenanceById( @PathVariable Long id )
    {
        MaintenanceDTO dto = maintenanceService.getMaintenanceById( id );
        return ResponseEntity.ok( dto );
    }

    @PostMapping( "/add" )
    public ResponseEntity<MaintenanceDTO> addMaintenance( @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        MaintenanceDTO created = maintenanceService.addMaintenance( dto );
        return ResponseEntity.ok( created );
    }

    @PutMapping( "/update/{id}" )
    public ResponseEntity<MaintenanceDTO> updateMaintenance( @PathVariable Long id, @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        MaintenanceDTO updated = maintenanceService.updateMaintenance( id, dto );
        return ResponseEntity.ok( updated );
    }

    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteMaintenance( @PathVariable Long id )
    {
        maintenanceService.deleteMaintenance( id );
        return ResponseEntity.noContent().build();
    }
}
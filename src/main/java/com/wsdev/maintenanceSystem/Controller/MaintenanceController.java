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
        List<MaintenanceDTO> list = maintenanceService.getMaintenances();
        return ResponseEntity.ok( list );
    }

    @Operation( description = "Irá buscar uma manutenção pelo 'ID' informado." )
    @GetMapping( "/{id}" )
    public ResponseEntity<MaintenanceDTO> getMaintenanceById( @PathVariable Long id )
    {
        MaintenanceDTO dto = maintenanceService.getMaintenanceById( id );
        return ResponseEntity.ok( dto );
    }

    @Operation( description = "Irá adicionar uma manutenção ao sistema." )
    @PostMapping( "/add" )
    public ResponseEntity<MaintenanceDTO> addMaintenance( @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        MaintenanceDTO created = maintenanceService.addMaintenance( dto );
        return ResponseEntity.ok( created );
    }

    @Operation( description = "Irá atualizar uma manutenção a partir do 'ID' informado." )
    @PutMapping( "/update/{id}" )
    public ResponseEntity<MaintenanceDTO> updateMaintenance( @PathVariable Long id, @Valid @RequestBody MaintenanceRequestDTO dto )
    {
        MaintenanceDTO updated = maintenanceService.updateMaintenance( id, dto );
        return ResponseEntity.ok( updated );
    }

    @Operation( description = "Irá deletar uma manutenção a partir do 'ID' informado." )
    @DeleteMapping( "/delete/{id}" )
    public ResponseEntity<Void> deleteMaintenance( @PathVariable Long id )
    {
        maintenanceService.deleteMaintenance( id );
        return ResponseEntity.noContent().build();
    }
}
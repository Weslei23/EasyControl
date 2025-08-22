package com.wsdev.maintenanceSystem.Controller;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import com.wsdev.maintenanceSystem.Utilities.PdfCreator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping( "/api/v1/pdf" )
@AllArgsConstructor
public class PdfCreatorController
{
    @Autowired
    private final PdfCreator pdfService;

    @PostMapping( "/maintenance" )
    public ResponseEntity<byte[]> gerarPdf( @RequestBody MaintenanceDTO dto ) throws IOException
    {
        try
        {
            byte[] pdfBytes = pdfService.gerarPdfManutencao( dto );

            return ResponseEntity.ok()
                    .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=manutencao.pdf" )
                    .contentType( MediaType.APPLICATION_PDF ).body( pdfBytes );
        }
        catch ( IOException ex )
        {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
        }
    }
}
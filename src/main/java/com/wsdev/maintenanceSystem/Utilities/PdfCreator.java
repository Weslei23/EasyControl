package com.wsdev.maintenanceSystem.Utilities;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfCreator
{
    public byte[] gerarPdfManutencao( MaintenanceDTO dto ) throws IOException
    {
        try ( PDDocument document = new PDDocument() )
        {
            PDPage page = new PDPage( PDRectangle.A4 );
            document.addPage( page );

            try ( PDPageContentStream content = new PDPageContentStream( document, page ) )
            {
                float yPosition = 0;
                content.beginText();
                content.setFont( PDType1Font.HELVETICA_BOLD, 18 );
                content.setLeading( 20f );
                content.newLineAtOffset( 50, 750 );

                content.showText( "Relatório de Manutenção" );
                content.newLine();

                content.setFont( PDType1Font.HELVETICA, 12 );
                content.newLine();
                content.showText( "Id: " + dto.id() );
                content.newLine();
                content.showText( "Cliente: " + dto.customerName() );
                content.newLine();
                content.showText( "Descrição: " + dto.descriptionService() );
                content.newLine();
                content.showText( "Status: " + dto.status() );
                content.newLine();
                content.showText( "Data Agendada: " + dto.scheduledDate() );
                content.endText();

                content.setStrokingColor(Color.LIGHT_GRAY); // cor das linhas (cinza claro)
                content.setLineWidth(0.5f); // espessura fina

                float margin = 50;
                float width = PDRectangle.A4.getWidth() - 2 * margin;
                float startY = PDRectangle.A4.getHeight() - margin; // topo da área útil
                float endY = margin; // margem inferior

                float lineSpacing = 10; // distância entre linhas em pontos

                for (float y = startY; y > endY; y -= lineSpacing) {
                    content.moveTo(margin, y);
                    content.lineTo(margin + width, y);
                    content.stroke();
                }

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save( out );
            return out.toByteArray();
        }
    }
}

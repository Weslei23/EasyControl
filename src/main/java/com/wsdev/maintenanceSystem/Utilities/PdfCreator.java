package com.wsdev.maintenanceSystem.Utilities;

import com.wsdev.maintenanceSystem.Dto.MaintenanceDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
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

            PDRectangle pageSize = page.getMediaBox();
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            float margin = 50f;
            float leading = 16f;
            float cursorY = pageHeight - margin;

            try ( PDPageContentStream content = new PDPageContentStream( document, page ) )
            {
                String title = "Relatório de Manutenção";
                int titleFontSize = 18;

                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth( title ) / 1000 * titleFontSize;
                float titleX = ( pageWidth - titleWidth ) / 2;

                content.beginText();
                content.setFont( PDType1Font.HELVETICA_BOLD, titleFontSize );
                content.newLineAtOffset( titleX, cursorY);
                content.showText( title );
                content.endText();

                cursorY -= 2 * leading;

                content.beginText();
                content.setFont( PDType1Font.HELVETICA, 12 );
                content.setLeading( leading );
                content.newLineAtOffset( margin, cursorY );

                content.showText( "Codígo da manutenção: " + dto.id() );
                content.newLine();
                content.showText( "Cliente: " + dto.customerName() );
                content.newLine();
                content.showText( "Descrição: " + dto.descriptionService() );
                content.newLine();
                content.showText( "Status: " + dto.status() );
                content.newLine();
                content.showText( "Data Agendada: " + dto.scheduledDate() );
                content.endText();

                cursorY -= 6 * leading;

                String linhaEmBranco = "________________________";

                content.beginText();
                content.setFont( PDType1Font.HELVETICA, 12 );
                content.newLineAtOffset( margin, cursorY );

                content.showText( "Data/Hora Início: " + linhaEmBranco + "    " );
                content.showText( "Data/Hora Fim: "   + linhaEmBranco );
                content.endText();

                cursorY -= 2 * leading;

                content.beginText();
                content.setFont( PDType1Font.HELVETICA_BOLD, 12 );
                content.newLineAtOffset( margin, cursorY );
                content.showText( "Materiais Utilizados:" );
                content.endText();

                cursorY -= leading;

                float notebookHeight = 200f;
                float notebookStartY = cursorY;
                float notebookEndY = cursorY - notebookHeight;
                float lineSpacing = 14f;

                content.setStrokingColor( Color.GRAY );
                content.setLineWidth( 0.5f );
                content.addRect( margin, notebookEndY, pageWidth - 2 * margin, notebookHeight );
                content.stroke();

                content.setStrokingColor( Color.LIGHT_GRAY );
                for ( float y = notebookStartY - lineSpacing; y > notebookEndY; y -= lineSpacing )
                {
                    content.moveTo( margin, y );
                    content.lineTo( pageWidth - margin, y );
                    content.stroke();
                }

                content.beginText();
                content.setFont( PDType1Font.HELVETICA, 12 );
                content.newLineAtOffset( margin, 350 );
                content.showText( "Assinatura do cliente: " + linhaEmBranco + "    " );
                content.endText();
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return out.toByteArray();
        }
    }
}

package com.irctc.sendpdfmail;

import com.irctc.bookings.entity.Bookings;
import com.irctc.dto.TicketDetailsDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfGenerator {

    public void generateTicketPDF(TicketDetailsDto data) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());

        String filePath = "/home/anurag/"+date+".pdf";
        System.out.println(filePath);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);

        Paragraph paragraph = new Paragraph("Ticket Details",font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Paragraph space = new Paragraph(" ");
        space.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(space);

        Paragraph paragraph2 = new Paragraph("Your Upcomming Trip Details are as follow: ");
        space.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph2);

        document.add(space);

        PdfPTable table = new PdfPTable(2);
        table.addCell("Train Name");
        table.addCell("123456");





        document.add(table);
        document.close();
        System.out.println("generated Succefullyt");

    }
}
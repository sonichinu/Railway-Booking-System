package com.irctc.sendpdfmail;

import com.irctc.bookings.entity.Bookings;
import com.irctc.dto.TicketDetailsDto;
import com.irctc.passengers.entity.Passengers;
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

        String filePath = "/home/rohit/"+date+".pdf";
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

        Paragraph paragraph2 = new Paragraph("Hey "+data.getUserName() +" Your Upcomming Trip Details are as follow: ");
        space.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph2);

        document.add(space);

        PdfPTable table = new PdfPTable(2);
        table.addCell("Train Name");
        table.addCell(data.getTrainName());

        table.addCell("Train Number");
        table.addCell(String.valueOf(data.getTrainNumber()));

        table.addCell("No Of Traveller");
        table.addCell(String.valueOf(data.getNoOfPassengers()));

        table.addCell("Book Date");
        table.addCell(date);

        table.addCell("Travel Date");
        table.addCell(String.valueOf(data.getTravelDate()));


        table.addCell("From Station");
        table.addCell(data.getFromStation());

        table.addCell("From Arrival TIme");
        table.addCell(data.getFromStationArrivalTIme()+" {24 Hour Format}");

        table.addCell("To Station");
        table.addCell(data.getToStation());

        table.addCell("To Arrival Time");
        table.addCell(data.getToStationArrivalTIme()+" {24 Hour Format}");

        table.addCell("Amount Paid");
        table.addCell(String.valueOf(data.getAmountPaid())+" INR");

        for(Passengers p : data.getPlist()){
            table.addCell("Passenger name");
            table.addCell(p.getName());
            table.addCell("Seat Number");
            table.addCell(p.getReservedSeat());
        }

        document.add(table);

        document.add(space);
        document.add(space);

        Paragraph paragraph3 = new Paragraph("From Your WellWisher");
        space.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph3);

        Paragraph paragraph4 = new Paragraph("Rohit Soni");
        space.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph4);

        document.close();
        System.out.println("generated Succefullyt");

    }
}
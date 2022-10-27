package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.service.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfServiceITextImpl implements PdfService {

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException {
        Path path = Paths.get(new File("res/arquitetura_v1.png").toURI());
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("res/iTextHelloWorld.pdf"));
        PdfPTable table = new PdfPTable(3);

        document.open();
        addTableHeader(table);
        addRows(table);
        addCustomRows(table);

        document.add(table);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
        String text = "Hello World\n";
        Chunk chunk = new Chunk(text, font);
        List<Paragraph> paragraphs = List.of(
                new Paragraph(chunk),
                new Paragraph(chunk),
                new Paragraph(chunk),
                new Paragraph(chunk)
        );

        Image img = Image.getInstance(path.toAbsolutePath().toString());
        document.add(img);

        paragraphs.forEach(paragraph -> {
            try {
                document.add(paragraph);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });
        document.close();

        PdfReader pdfReader = new PdfReader("res/iTextHelloWorld.pdf");
        PdfStamper pdfStamper
                = new PdfStamper(pdfReader, new FileOutputStream("res/encryptedPdf.pdf"));
        pdfStamper.setEncryption(
                "userpass".getBytes(),
                "password".getBytes(),
                0,
                PdfWriter.ENCRYPTION_AES_256
        );

        pdfStamper.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private static void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
        Path path = Paths.get(new File("res/arquitetura_v1.png").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell(img);
        table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }

}

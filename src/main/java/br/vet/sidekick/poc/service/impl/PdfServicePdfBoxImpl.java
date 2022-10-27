package br.vet.sidekick.poc.service.impl;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import br.vet.sidekick.poc.service.PdfService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfServicePdfBoxImpl implements PdfService {

    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage();
        document.addPage(page1);
//        Path pathToPdf = Paths.get(new File("res/PDFBOX/pdfBoxHelloWorld.pdf").toURI());

        PDPageContentStream contentStream = new PDPageContentStream(document, page1);

        Path pathToImage = Paths.get(new File("res/arquitetura_v1.png").toURI());
        PDImageXObject image = PDImageXObject.createFromFile(pathToImage.toAbsolutePath().toString(), document);
        contentStream.drawImage(image, 0, 0);
        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText("Hello World");
        contentStream.endText();
        //Dummy Table
        float margin = 50;
// starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page1.getMediaBox().getHeight() - (2 * margin);
// we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page1.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
// y position is your coordinate of top left corner of the table
        float yPosition = 750;

        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page1, true, drawContent);


        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Header");
        table.addHeaderRow(headerRow);


        Row<PDPage> row = table.createRow(12);
        cell = row.createCell(30, "Data 1");
        cell = row.createCell(70, "Some value");

        table.draw();

//        mainDocument.addPage(myPage);
//        mainDocument.save("testfile.pdf");
//        mainDocument.close();

        contentStream.close();

        document.save("res/PDFBOX/pdfBoxImage.pdf");
//        document.save(pathToImage.toAbsolutePath().toString());
        document.close();
    }
}

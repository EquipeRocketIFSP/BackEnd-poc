package br.vet.sidekick.poc.service.impl;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class PdfServicePdfBoxImpl implements PdfService {
    private static double mm = 1 / 72 * 25.4 / 72;

    public byte[] termoAutorizacaoProcedimentoCirurgico(
            String estabelecimento,
            String procedimento,
            String cidade,
            Animal animal,
            Veterinario veterinario,
            Tutor tutor
    ) throws IOException {
        String title = "TERMO DE AUTORIZAÇÃO PARA PROCEDIMENTO CIRÚRGICO";
        String section1 = MessageFormat.format(
                "Autorizo a realização do procedimento cirúrgico {0} no animal de nome {1}, espécie {2}, " +
                        "raça {3}, sexo {4}, idade (real ou aproximada) {5}, pelagem {6}, outras informações que " +
                        "possibilitem a identificação do animal (ex. microchip) a ser realizado pela(o) " +
                        "médica(o)-vetenária(o){7} (CMRV-{8})",
                procedimento,
                animal.getNome(),
                animal.getEspecie(),
                animal.getRaca(),
                animal.getSexo(),
                animal.getIdade(),
                animal.getPelagem(),
                veterinario.getNome(),
                veterinario.getRegistroCRMV()
                );
        String section2 = "Identificação do responsável pelo animal:";
        String section3 = MessageFormat.format(
                "Nome: {0}<br>RG: {1}<br>CPF {2}<br>Endereço completo: {3}<br>Telefone: {4}<br>email: {5}",
                tutor.getNome(),
                tutor.getRg(),
                tutor.getCpf(),
                tutor.getEndereco(),
                tutor.getTelefone(),
                tutor.getEmail()
        );
        String section4 = "Declaro ter sido esclarecido acerca dos possíveis riscos inerentes durante ou após a " +
                "realização do procedimento cirúrgico citado, estando o referido profissional isento de quaiSquer " +
                "responsabilidades decorrentes de tais riscos.";
        Date today = new Date();
        String section5 = MessageFormat.format(
                "{0}, {1,date,dd} de {2} de {1,date,yyyy}",
                "São Paulo",
                today,
                getExtenseMonth(today)
        );

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        try {
            contentStream.setFont(PDType1Font.COURIER, 16);

            float margin = 50;//Dummy Table
            float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);// starting y position is whole page height subtracted by top and bottom margin
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);// we want table across whole page width (subtracted by left and right margin ofcourse)
            boolean drawContent = true;
            float bottomMargin = 70;
            float yPosition = 750;// y position is your coordinate of top left corner of the table

            BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);


            Row<PDPage> headerRow = table.createRow(15f);
            Cell<PDPage> cell = headerRow.createCell(100, title);
            cell.setFont(PDType1Font.HELVETICA_BOLD);
            table.addHeaderRow(headerRow);

            Row<PDPage> row1 = table.createRow(12);
            cell = row1.createCell(100, section1);
            cell.setFont(PDType1Font.HELVETICA);

            Row<PDPage> row2 = table.createRow(12);
            cell = row2.createCell(100, section2);
            cell.setFont(PDType1Font.HELVETICA);

            Row<PDPage> row3 = table.createRow(12);
            cell = row3.createCell(100, section3);
            cell.setFont(PDType1Font.HELVETICA);

            Row<PDPage> row4 = table.createRow(12);
            cell = row4.createCell(100, section4);
            cell.setFont(PDType1Font.HELVETICA);

            Row<PDPage> row5 = table.createRow(12);
            cell = row5.createCell(100, section5);
            cell.setFont(PDType1Font.HELVETICA);

            table.draw();
            contentStream.close();

            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanModify(true);
            accessPermission.isOwnerPermission();

            document.protect(new StandardProtectionPolicy("ownerpass", "userpass", accessPermission));

            document.save("res/mockSample.pdf");
            document.close();
        } catch (IOException e){
            log.error(e.getLocalizedMessage());
            throw e;
        }
        return null;
    }

    private static String getExtenseMonth(Date today) {
        return StringUtils.capitalize(
                MessageFormat.format("{0,date,MMMM}", today)
        );
    }

    public static void run() throws IOException {

        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage();
        document.addPage(page1);
//        Path pathToPdf = Paths.get(new File("res/PDFBOX/pdfBoxHelloWorld.pdf").toURI());

        PDPageContentStream contentStream = new PDPageContentStream(document, page1);

//        Path pathToImage = Paths.get(new File("res/arquitetura_v1.png").toURI());
//        PDImageXObject image = PDImageXObject.createFromFile(pathToImage.toAbsolutePath().toString(), document);
//        contentStream.drawImage(image, 0, 0);
//        contentStream.setFont(PDType1Font.COURIER, 12);
//        contentStream.beginText();
//        contentStream.newLineAtOffset(50, 700);
//        contentStream.showText("Hello World");
//        contentStream.endText();
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
        Row<PDPage> row2 = table.createRow(12);
        cell = row2.createCell(70, "Data 1");

        table.draw();

//        mainDocument.addPage(myPage);
//        mainDocument.save("testfile.pdf");
//        mainDocument.close();

        contentStream.close();

        document.save("res/PDFBOX/pdfBoxImage.pdf");
//        document.save(pathToImage.toAbsolutePath().toString());
        document.close();
    }

    public static void main(String[] args) throws IOException {
        new PdfServicePdfBoxImpl().termoAutorizacaoProcedimentoCirurgico(
                "estabelecimento",
                "Operação",
                "São Paulo",
                Animal.builder()
                        .especie("cachorro")
                        .raca("vira-lata")
                        .sexo("masc")
                        .idade(2)
                        .nome("doguinho")
                        .pelagem("curta")
                        .outros("chip 123456485312")
                        .build(),
                Veterinario.builder()
                        .registroCRMV("SP-12345")
                        .nome("VET")
                        .build(),
                Tutor.builder()
                        .cpf("228.831.350-10")
                        .rg("12345689")
                        .email("email@valido.com")
                        .endereco("endereco random")
                        .telefone("123456789")
                        .build()
        );


//        run();

    }
}

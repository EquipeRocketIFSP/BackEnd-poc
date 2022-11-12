package br.vet.sidekick.poc.service.impl;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import br.vet.sidekick.poc.model.*;
import br.vet.sidekick.poc.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * !! Não é possível adicionar "\n" nas strings. se necessário, adicione uma nova linha ou aumente o tamanho da caixa
 */
@Service
@Slf4j
public class PdfServicePdfBoxImpl implements PdfService {
//    private static double mm = 1 / 72 * 25.4 / 72;
    final private static float margin = 50;
    final private static boolean drawContent = true;
    final private static float bottomMargin = 70;
    final private static float yPosition = 750;
    static private void writeException(Exception e) throws Exception {
        log.error(String.format("{\"ERROR\": \"%s\", \"StackTrace\": \"%s\"}", e.getLocalizedMessage(), e.getStackTrace()));
        throw e;
    }

    private static AccessPermission setAccessPermission() {
        AccessPermission accessPermission = new AccessPermission();
        accessPermission.setCanModify(true);
        accessPermission.isOwnerPermission();
        return accessPermission;
    }

    @Override
    public byte[] termoAutorizacaoProcedimentoCirurgico(
            String estabelecimento,
            String procedimento,
            String cidade,
            Animal animal,
            Veterinario veterinario,
            Tutor tutor
    ) throws Exception {
        // TODO: passar essas variáveis para configuração
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

        final PDDocument document = new PDDocument();
        final PDPage page = new PDPage();
        document.addPage(page);
        final PDPageContentStream contentStream = new PDPageContentStream(document, page);
        final float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);// starting y position is whole page height subtracted by top and bottom margin
        final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);// we want table across whole page width (subtracted by left and right margin ofcourse)

        try {
            contentStream.setFont(PDType1Font.COURIER, 16);
            BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);

            Row<PDPage> headerRow = table.createRow(15f);
            headerRow.createCell(100, title).setFont(PDType1Font.HELVETICA_BOLD);
            table.addHeaderRow(headerRow);

            Row<PDPage> row1 = table.createRow(12);
            row1.createCell(100, section1).setFont(PDType1Font.HELVETICA);

            Row<PDPage> row2 = table.createRow(12);
            row2.createCell(100, section2).setFont(PDType1Font.HELVETICA);

            Row<PDPage> row3 = table.createRow(12);
            row3.createCell(100, section3).setFont(PDType1Font.HELVETICA);

            Row<PDPage> row4 = table.createRow(12);
            row4.createCell(100, section4).setFont(PDType1Font.HELVETICA);

            Row<PDPage> row5 = table.createRow(12);
            row5.createCell(100, section5).setFont(PDType1Font.HELVETICA);

            table.draw();
            contentStream.close();

            document.protect(new StandardProtectionPolicy(
                    "ownerpass",
                    "userpass",
                    setAccessPermission()));

            document.save("res/mockSample.pdf");
            document.close();
        } catch (IOException e){
            writeException(e);
        }
        return null;
    }

    @Override
    public byte[] writeProntuario(Prontuario prontuario) throws Exception {
        // TODO: Passar essas variáveis para arquivo de configuração
        final String title = "Prontuário Clínico veterinário";
        final String vetQualificacaoL1 = prontuario.getVeterinario().getNome();
        final String vetQualificacaoL2 = "Crmv: "+ prontuario.getVeterinario().getRegistroCRMV();
        final String clinicaQualificacao = "prontuario.getClinica().getRazaoSocial()" + " - Telefones: " + "prontuario.getClinica().getTelefones().toString()" + " - Endereço: " + "prontuario.getClinica().getLogradouro()";

        try(final PDDocument document = new PDDocument()){
            final PDPage page = new PDPage();
            document.addPage(page);
            try(final PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                final float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);// starting y position is whole page height subtracted by top and bottom margin
                final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);// we want table across whole page width (subtracted by left and right margin ofcourse)

                contentStream.setFont(PDType1Font.COURIER, 16);

                BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);


                Row<PDPage> headerRow = table.createRow(15f);
                Map.of(70, title,
                        30, String.valueOf(prontuario.getId())
                ).forEach(
                        (width, value) -> headerRow.createCell(width, value).setFont(PDType1Font.HELVETICA_BOLD)
                );

                table.addHeaderRow(headerRow);

                Cell<PDPage> cell;
                Row<PDPage> row1 = table.createRow(12);
                cell = row1.createCell(100, vetQualificacaoL1);
                cell.setFont(PDType1Font.HELVETICA);
                Row<PDPage> row2 = table.createRow(12);
                cell = row2.createCell(100, vetQualificacaoL2);

                cell.setFont(PDType1Font.HELVETICA);

                Row<PDPage> row3 = table.createRow(24);
                row3.createCell(100, clinicaQualificacao).setFont(PDType1Font.HELVETICA);

                Row<PDPage> row4 = table.createRow(12);
                row3.createCell(25, "Animal").setFont(PDType1Font.HELVETICA);
//              row3.createCell(70, prontuario.getAnimal().getNome()       ); //TODO: Quando a classe Animal estiver minimamente integrada com o prontuário, passar a string correta
                row4.createCell(75, "prontuario.getAnimal().getNome()").setFont(PDType1Font.HELVETICA);

                Row<PDPage> row5 = table.createRow(12);
                List.of("Espécie",
                        "Sexo",
                        "Idade",
                        "Microchip"
                ).forEach(
                        headerCell -> row5.createCell(25, headerCell).setFont(PDType1Font.HELVETICA)
                );

                Row<PDPage> row6 = table.createRow(12);
                List.of("prontuario.getAnimal().getEspecie()",//TODO: Quando a classe Animal estiver minimamente integrada com o prontuário, passar a string correta
                        "prontuario.getAnimal().getSexo()",
                        "String.valueOf(prontuario.getAnimal().getIdade())",
                        "prontuario.getAnimal().setOutros()"
                ).forEach(
                        bodyCell -> row6.createCell(25, bodyCell).setFont(PDType1Font.HELVETICA)
                );

                table.draw();
            } catch (IOException e){
                writeException(e);
            }

//            contentStream.close();
//            AccessPermission accessPermission = new AccessPermission();
//            accessPermission.setCanModify(true);
//            accessPermission.isOwnerPermission();
//
//            document.protect(new StandardProtectionPolicy("ownerpass", "userpass", accessPermission));

            document.save("res/mockSample2.pdf");
        } catch (IllegalArgumentException e){
            writeException(e);
        }

        return new byte[0];
    }

    private static String getExtenseMonth(Date today) {
        return StringUtils.capitalize(
                MessageFormat.format("{0,date,MMMM}", today)
        );
    }

    public static void main(String[] args) throws Exception {
//        new PdfServicePdfBoxImpl().termoAutorizacaoProcedimentoCirurgico(
//                "estabelecimento",
//                "Operação",
//                "São Paulo",
//                Animal.builder()
//                        .especie("cachorro")
//                        .raca("vira-lata")
//                        .sexo("masc")
//                        .idade(2)
//                        .nome("doguinho")
//                        .pelagem("curta")
//                        .outros("chip 123456485312")
//                        .build(),
//                Veterinario.builder()
//                        .registroCRMV("SP-12345")
//                        .nome("VET")
//                        .build(),
//                Tutor.builder()
//                        .cpf("228.831.350-10")
//                        .rg("12345689")
//                        .email("email@valido.com")
//                        .endereco("endereco random")
//                        .telefone("123456789")
//                        .build()
//        );
        new PdfServicePdfBoxImpl().writeProntuario(
                Prontuario.builder()
                        .tutor(Tutor.builder().id(1L).build())
                        .veterinario(Veterinario.builder().id(1L).build())
                        .animal(Animal.builder().id(1L).build())
                        .dataAtendimento(LocalDateTime.now())
                        .procedimentos(List.of(Procedimento.builder()
                                .tipoProcedimento(Procedimento.TipoProcedimento.valueOf("IMUNIZACAO"))
                                .tipo(Procedimento.Tipo.valueOf("PRESCRITIVO"))
                                .descricao("Procedimento")
                                .build()))
                        .cirurgia(Cirurgia.builder()
                                .asa(Cirurgia.ASA.valueOf("ASA1"))
                                .tipo(Cirurgia.TipoCirurgia.valueOf("CASTRACAO"))
                                .build())
                        .prescricoes(List.of(Prescricao.builder()
                                                .descricao("Medicacao muito longa e cheia de caracteres")
                                                .build()))
                        .diagnostico("Mussum Ipsum, cacilds vidis litro abertis. Nullam volutpat risus nec leo commodo, ut interdum diam laoreet. Sed non consequat odio.Quem num gosta di mim que vai caçá sua turmis!Quem num gosta di mé, boa gentis num é.Manduma pindureta quium dia nois paga.")
                        .observacoes("Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis.Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis.Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget.Quem num gosta di mim que vai caçá sua turmis!")
//                .documentos(getAutorizacoes().stream()
//                        .map(aut -> Documento.builder()
//                                .build())
//                        .collect(Collectors.toList()))
                        .prontuarioOrigem(null)
                        .build()
        );

    }
}

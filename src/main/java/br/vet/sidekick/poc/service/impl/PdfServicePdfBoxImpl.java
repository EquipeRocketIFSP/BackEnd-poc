package br.vet.sidekick.poc.service.impl;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.datatable.DataTable;
import br.vet.sidekick.poc.model.*;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.PdfRepository;
import br.vet.sidekick.poc.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.ProtectionPolicy;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * !! Não é possível adicionar "\n" nas strings. se necessário, adicione uma nova linha ou aumente o tamanho da caixa
 */
@Service
@Slf4j
public class PdfServicePdfBoxImpl implements PdfService {

    @Autowired
    private PdfRepository pdfRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    private static List<Path> files = new ArrayList<>();
//    private static double mm = 1 / 72 * 25.4 / 72;
    final private static float margin = 50;
    final private static boolean drawContent = true;
    final private static float bottomMargin = 70;
    final private static float yPosition = 750;

    @Value("${app.default.pdf.pass}")
    private String defaultPass;
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

    public String getProntuarioName(Prontuario prontuario){
        return "prontuario_"+prontuario.getId()+".pdf";
    }
    @Override
    public byte[] writeProntuario(Prontuario prontuario) throws Exception {
        // TODO: Passar essas variáveis para arquivo de configuração
        final String title = "Prontuário Clínico veterinário";
        final String vetQualificacaoL1 = "prontuario.getVeterinario().getNome()";
        final String vetQualificacaoL2 = "Crmv: "+ "prontuario.getVeterinario().getRegistroCRMV()";
        final String clinicaQualificacao0 = "prontuario.getClinica().getRazaoSocial()";
        final String clinicaQualificacao1 = "prontuario.getClinica().getTelefones().toString()";

        final String fileName = "res/" + getProntuarioName(prontuario);

        try(final PDDocument document = new PDDocument()){
            final PDPage page = new PDPage();
            document.addPage(page);
            try(final PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                final float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);// starting y position is whole page height subtracted by top and bottom margin
                final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);// we want table across whole page width (subtracted by left and right margin ofcourse)

                contentStream.setFont(PDType1Font.COURIER, 16);

                BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
                DataTable t = new DataTable(table, page);
                Row<PDPage> headerRow = table.createRow(15f);
                Map<Float, String> map = new LinkedHashMap<>();
                map.put(70f, title);
//                map.put(30f, "Prontuário: " + String.valueOf(prontuario.getId())); //TODO: Ajustar para capturar a numeração do prontuário
                map.put(30f, "Prontuário: numeroMock");
                map.forEach(
                        (width, value) -> headerRow.createCell(width, value).setFont(PDType1Font.HELVETICA_BOLD)
                );

                table.addHeaderRow(headerRow);

                table.createRow(15f).createCell(100f, "");

                Row<PDPage> row1 = table.createRow(12);
                createCellDefaulFont(row1, 100f, vetQualificacaoL1);

                Row<PDPage> row2 = table.createRow(12);
                createCellDefaulFont(row2, 100f, vetQualificacaoL2);

                table.createRow(15f).createCell(100f, "");

                Row<PDPage> row3 = table.createRow(12);
                createCellDefaulFont(row3, 100f, clinicaQualificacao0);

                Row<PDPage> row3_0 = table.createRow(12);
                createCellDefaulFont(row3_0,25f, "Telefones");
                createCellDefaulFont(row3_0,75f, clinicaQualificacao1);

                table.createRow(15f).createCell(100f, "");

                Row<PDPage> row4 = table.createRow(12);
                createCellDefaulFont(row4, 25f, "Animal");
//              row4.createCell(70, prontuario.getAnimal().getNome()       ); //TODO: Quando a classe Animal estiver minimamente integrada com o prontuário, passar a string correta
                createCellDefaulFont(row4, 75f, "prontuario.getAnimal().getNome()");

                Row<PDPage> row7 = table.createRow(12);
                Map.of(
                        25f, "Raça",
                        75f, "prontuario.getAnimal().getRaca()"
                ).forEach(
                        (width, value) -> createCellDefaulFont(row7, width, value)
                );

                t.addListToTable(
                        List.of(
                                List.of("Espécie",
                                        "Sexo",
                                        "Idade",
                                        "Microchip"
                                ),
                                List.of("prontuario.getAnimal().getEspecie()",
                                        "prontuario.getAnimal().getSexo()",
                                        "String.valueOf(prontuario.getAnimal().getIdade())",
                                        "prontuario.getAnimal().getOutros()"
                                )
                        ),
                        DataTable.NOHEADER
                );

//                Row<PDPage> row5 = table.createRow(12f);
//                List.of("Espécie",
//                        "Sexo",
//                        "Idade",
//                        "Microchip"
//                ).forEach(
//                        headerCell -> createCellDefaulFont(row5, 25f, headerCell)
//                );
//
//                Row<PDPage> row6 = table.createRow(12); //TODO: Quando a classe Animal estiver minimamente integrada com o prontuário, passar a string correta
//                List.of("prontuario.getAnimal().getEspecie()",
//                        "prontuario.getAnimal().getSexo()",
//                        "String.valueOf(prontuario.getAnimal().getIdade())",
//                        "prontuario.getAnimal().getOutros()"
//                ).forEach(
//                        bodyCell -> createCellDefaulFont(row6, 25f, bodyCell)
//                );

                table.createRow(15f).createCell(100f, "");

                Row<PDPage> row8 = table.createRow(12); // TODO: Alterar para dados reais
                Map.of(
                        10f, "Tutor",
                        50f, "prontuario.getTutor().getNome()"
                ).forEach(
                        (width, value) -> createCellDefaulFont(row8, width, value)
                );
                Map.of(
                        10f, "CPF",
                        30f, "prontuario.getTutor().getCpf()"
                ).forEach(
                        (width, value) -> createCellDefaulFont(row8, width, value)
                );


                Row<PDPage> row9 = table.createRow(12); // TODO: Alterar para dados reais
                map = new LinkedHashMap<>();
                map.put(10f, "Endereço");
                map.put(50f, "prontuario.getTutor().getNome()");
                map.forEach(
                        (width, value) -> createCellDefaulFont(row9, width, value)
                );
                map = new LinkedHashMap<>();
                map.put(10f, "Telefone");
                map.put(
//                        30f, prontuario.getTutor().getTelefones()
//                                .stream()
//                                .filter(phone -> phone.getTipo() == "Contato")
//                                .collect(Collectors.toList()
//                                ).get(0)
//                                .getNumeracao() // TODO: Adicinar atributo com o telefone principal
                        30f, "prontuario.getTutor().getTelefones()"
                );
                map.forEach(
                        (width, value) -> createCellDefaulFont(row9, width, value)
                );

                table.createRow(15f).createCell(100f, "");

                Row<PDPage> row10 = table.createRow(12f);

                var date = prontuario.getDataAtendimento();
                map = new LinkedHashMap<>();
                map.put(10f, "Data");
                map.put(40f, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
//                map.put(40f, new SimpleDateFormat("dd/MM/yyyy").format(date)); // TODO: Descomentar essa string para trazer dados do prontuário
                map.forEach(
                        (width, value) -> createCellDefaulFont(row10, width, value)
                );
                map.put(10f, "Horário");
                map.put(40f, new SimpleDateFormat("hh:mm:ss").format(new Date()));
//                map.put(40f, new SimpleDateFormat("hh:mm:ss").format(date)); // TODO: Descomentar essa string para trazer dados do prontuário
                map.forEach(
                        (width, value) -> createCellDefaulFont(row10, width, value)
                );

                table.createRow(30f).createCell(100f, "");

                Map<Integer, Map<Integer, Float>> frameYX = Map.of(
                        0, Map.of(
                                0,16f,
                                1, 100f),
                        1, Map.of(
                                0, 32f,
                                1, 100f),
                        2, Map.of(
                                0,64f,
                                1,100f)
                );
                Map<String, Map> itens = new LinkedHashMap<>();
                itens.put("Imunizações", frameYX.get(0));
                itens.put("Sinais Clínicos", frameYX.get(1));
                itens.put("Exames", frameYX.get(0));
                itens.put("Prescrições", frameYX.get(0));
                itens.put("Diagnóstico", frameYX.get(0));
                itens.put("Observações", frameYX.get(2));

                itens.forEach(
                        (cellValue, frame) -> {
                            createCellDefaulFont(table.createRow(12f), 100f, cellValue);
                            table.createRow((Float) frame.get(0)).createCell((Float) frame.get(1), "");
                        }
                );

                table.draw();
            } catch (IOException e){
                writeException(e);
            }

            document.protect(getProtectionPolicy(defaultPass, getTutorPass(prontuario)));
            document.save(fileName);
        } catch (IllegalArgumentException e){
            writeException(e);
        }
        Path path = Path.of(fileName);
        pdfRepository.putObject(
                clinicaRepository.findById(prontuario.getClinica().getId()).stream()
                        .findFirst()
                        .orElseThrow(SQLException::new)
                        .getCnpj(),
                fileName.substring(fileName.indexOf("/")+1),
                path.toFile()
        );
        try {
            return Files.readAllBytes(path);
        } finally {
            Files.deleteIfExists(path);
        }
    }

    private static String getTutorPass(Prontuario prontuario) {
        return prontuario.getTutor()
                .getCpf()
                .replace(".", "")
                .substring(5, 9);
    }

    private static ProtectionPolicy getProtectionPolicy(String defaultPass, String tutorPass) throws IOException {
        final AccessPermission accessPermission = new AccessPermission();
        accessPermission.setCanModify(true);
        accessPermission.setCanExtractForAccessibility(true);
        accessPermission.setCanPrint(true);
        accessPermission.setCanExtractContent(true);
        var std = new StandardProtectionPolicy(defaultPass, tutorPass, accessPermission);
        log.info(std.getOwnerPassword());
        return std;
    }

    @Override
    public byte[] retrieveFromRepository(Prontuario prontuario) throws IOException {
        return pdfRepository.retrieveObject(
                prontuario.getClinica().getCnpj(),
                getProntuarioName(prontuario)
        );
    }

    private Cell<PDPage> createCellDefaulFont(Row<PDPage> row1, Float width, String value) {
        Cell<PDPage> cell = row1.createCell(width, value);
        cell.setFont(PDType1Font.HELVETICA);
        return cell;
    }

    private String getExtenseMonth(Date today) {
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

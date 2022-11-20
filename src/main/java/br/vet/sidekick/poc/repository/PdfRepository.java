package br.vet.sidekick.poc.repository;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public interface PdfRepository {
    void putObject(String cnpj, String keyName, File filePath);

    byte[] retrieveObject(String cnpj, String keyName) throws IOException;
}

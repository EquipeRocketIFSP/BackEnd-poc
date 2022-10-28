package br.vet.sidekick.poc.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
public class S3BucketService {

    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
    private static final String SUCESSFULLY_SAVED = "Arquivo salvo com sucesso: ";

    @Value("app.temp.path")
    private static String RESOURCE_PATH;

    public static Bucket getBucket(String bucket_name) throws AmazonS3Exception {
        return s3.listBuckets()
                .stream()
                .filter(b -> b.getName().equals(bucket_name))
                .findFirst()
                .orElseThrow(() -> new AmazonS3Exception("Bucket não identificado"));
    }

    public static Bucket createOrRetrieveBucket(String bucket_name) {
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
            log.info("Bucket " + bucket_name + " already exists.\n");
            b = getBucket(bucket_name);
        } else {
            try {
                b = s3.createBucket(bucket_name);
                log.info("Bucket criado com sucesso: " + bucket_name);
            } catch (AmazonS3Exception e) {
                log.error(e.getErrorMessage());
            }
        }
        return b;
    }

    public static File retrieveObject(String bucket_name, String key_name) {

        S3Object o = s3.getObject(bucket_name, key_name);
        Integer dot = key_name.indexOf(".");
        String s = "../"+ key_name.substring(0, dot) + "_v1" + key_name.substring(dot);
        log.info("buscando por: " + s + " no sistema");

        File file = null;
        try {
            file = new File(s);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        if(file == null) {
            log.error("Nenhum arquivo identificado para o nome: " + s);
            return null;
        }
        try (
                S3ObjectInputStream s3is = o.getObjectContent();
                FileOutputStream fos = new FileOutputStream(file);
                ){
            log.info("Dados recuperados, realizando armazenamento em: " + s);
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            return file;
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
            return null;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static void putObject(String bucketName, String key_name, String file_path){
        Bucket b = createOrRetrieveBucket(bucketName);
        log.info("keyName: "+ key_name);
        File file = null;
        try{
            file = retrieveObject(bucketName, key_name);
        } catch (AmazonS3Exception e){
            log.warn("Arquivo não identificado. Gravando...");
        }
        if (file != null){
            log.info("Arquivo identificado: " + file.getName() + ". Não será gravado");
            return;
        }
        try {
            s3.putObject(bucketName, key_name, new File(file_path));
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
        }
        log.info(SUCESSFULLY_SAVED + key_name);
    }

//    public static void main(String[] args){
//        String bucket_name = args[0];
//        String file_path = args[1];
//        String key_name = Paths.get(file_path).getFileName().toString();
//
//        putObject(bucket_name, key_name, file_path);
//
//        File f = retrieveObject(bucket_name, key_name);
//
//        log.info("Armazenado em: " + f.getPath());
//    }
}

package br.vet.sidekick.poc.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S3BucketService {

    private static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        public static Bucket getBucket(String bucket_name) throws AmazonS3Exception {
            return s3.listBuckets()
                    .stream()
                    .filter(b -> b.equals(bucket_name))
                    .findFirst()
                    .orElseThrow(() -> new AmazonS3Exception("Bucket não identificado"));
        }

        public static Bucket createBucket(String bucket_name) {
            Bucket b = null;
            if (s3.doesBucketExistV2(bucket_name)) {
                log.info("Bucket " + bucket_name + " already exists.\n");
                b = getBucket(bucket_name);
            } else {
                try {
                    b = s3.createBucket(bucket_name);
                } catch (AmazonS3Exception e) {
                    log.error(e.getErrorMessage());
                }
            }
            return b;
        }
}

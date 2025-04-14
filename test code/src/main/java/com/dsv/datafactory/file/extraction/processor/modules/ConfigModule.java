package com.dsv.datafactory.file.extraction.processor.modules;

import com.dsv.datafactory.file.extraction.processor.Config;
import com.google.cloud.vision.v1.Feature;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import static com.dsv.datafactory.file.extraction.processor.util.ConfigurationLoader.getOrDefault;
import static com.dsv.datafactory.file.extraction.processor.util.ConfigurationLoader.getOrFail;

public class ConfigModule implements Module {

    @Override
    public void configure(Binder binder) {
    }

    @Provides
    @Singleton
    public Config provideConfig() {
        try {
            //TODO: Add to constant enum values that make sense to be there.
            Config.Builder configBuilder = new Config.Builder()
                    // Required parameters
                    .kafkaClientId(getOrFail("KAFKA_CLIENT_ID"))
                    .kafkaGroupId(getOrFail("KAFKA_GROUP_ID"))
                    .imageExtractionMetadataTopic(getOrFail("IMAGE_EXTRACTION_METADATA_TOPIC"))
                    .extractedDocumentTopic(getOrFail("EXTRACTED_DOCUMENTS_TOPIC"))

                    .enableKafkaSSL(getOrDefault("ENABLE_KAFKA_SSL", "true"))
                    .enableRBAC(getOrDefault("ENABLE_KAFKA_RBAC", "false"))
                    .kafkaAutoOffsetReset(getOrDefault("KAFKA_AUTO_OFFSET_RESET", "latest"))
                    .kafkaCommitIntervalMs(Integer.parseInt(getOrDefault("KAFKA_COMMIT_INTERVAL_MS", "200")))
                    .kafkaPollIntervalMs(Integer.parseInt(getOrDefault("KAFKA_POLL_INTERVAL_MS", "1800000")))
                    .kafkaRequestTimeoutMs(Integer.parseInt(getOrDefault("REQUEST_TIMEOUT_MS_CONFIG", "60000")))
                    .kafkaBootstrapServers(getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092"))
                    .kafkaMaxRequestSize(getOrDefault("KAFKA_MAX_REQUEST_SIZE", "104857600"))
                    .kafkaTruststorePath(getOrDefault("KAFKA_TRUSTSTORE_PATH", ""))
                    .kafkaTruststoreFile(getOrDefault("KAFKA_TRUSTSTORE_FILE", ""))
                    .kafkaTruststorePassword(getOrDefault("KAFKA_TRUSTSTORE_PASSWORD", ""))
                    .kafkaSLLProtocol(getOrDefault("KAFKA_SSL_PROTOCOL", "TLSv1.3,TLSv1.2"))
                    .kafkaSSLCipher(getOrDefault("KAFKA_SSL_CIPHER_SUITE",
                            "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"))
                    .googleCredPath(getOrDefault("GOOGLE_APPLICATION_CREDENTIALS", "credentials/google-cred.json"))
                    .runGVInPararell(getOrDefault("RUN_GV_PARALLEL", "false"))
                    .feature(Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build())
                    .goodnessOfFit(getOrDefault("GOODNESS_OF_FIT", "0.999"))
                    .startNumberOfClasses(getOrDefault("START_CLASSES", ""))
                    .lineServiceUrl(getOrDefault("JENKS_URI", "http://aif-jenks:8005/jenks/clustering"));

            return configBuilder.build();
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing numeric configuration values", e);
        } catch (IllegalStateException e) {
            throw new RuntimeException("Error in configuration: " + e.getMessage(), e);
        }
    }
}

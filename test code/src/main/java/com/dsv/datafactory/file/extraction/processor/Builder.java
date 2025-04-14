package com.dsv.datafactory.file.extraction.processor;

import com.dsv.datafactory.file.extraction.processor.util.Constants;

public class Builder {
    // Kafka configuration
    private String kafkaClientId;
    private String kafkaGroupId;
    private String kafkaAutoOffsetReset = Constants.LATEST.getValue();
    private int kafkaCommitIntervalMs = 200;
    private String enableKafkaSSL = Constants.TRUE.getValue();
    private String kafkaBootstrapServers = Constants.KAFKA_BOOT_STRAP_SERVERS_LOCALHOST.getValue();
    private String kafkaMaxRequestSize = Constants.KAFKA_MAX_REQUEST_SIZE.getValue();
    private String kafkaTruststorePath = "";
    private String kafkaTruststoreFile = "";
    private String kafkaTruststorePassword = "";
    private String kafkaSLLProtocol = Constants.KAFKA_SLL_PROTOCOL.getValue();
    private String kafkaSSLCipher = Constants.KAFKA_SSL_CIPHER.getValue();
    private int kafkaPollIntervalMs = 1800000;
    private int kafkaRequestTimeoutMs = 60000;
    private String enableRBAC = Constants.FALSE.getValue();

    // Topic configuration
    private String imageExtractionMetadataTopic;
    private String extractedDocumentTopic;

    // Google Vision configuration
    private Feature feature;
    private String googleCredPath = Constants.GOOGLE_CRED_PATH.getValue();
    //TODO: Update name to Parallel
    private String runGVInParallel = Constants.FALSE.getValue();;

    // Processing configuration
    private String lineServiceUrl = Constants.LINE_SERVICE_URL.getValue();
    private String startNumberOfClasses = "";
    private String goodnessOfFit = Constants.GOODNESS_OF_FIT.getValue();


    public Config build() {
        // Validate required fields
        if (kafkaClientId == null || kafkaClientId.isEmpty()) {
            throw new IllegalStateException("kafkaClientId is required");
        }
        if (kafkaGroupId == null || kafkaGroupId.isEmpty()) {
            throw new IllegalStateException("kafkaGroupId is required");
        }
        if (imageExtractionMetadataTopic == null || imageExtractionMetadataTopic.isEmpty()) {
            throw new IllegalStateException("imageExtractionMetadataTopic is required");
        }
        if (extractedDocumentTopic == null || extractedDocumentTopic.isEmpty()) {
            throw new IllegalStateException("extractedDocumentTopic is required");
        }
        if (feature == null) {
            // Set default feature if not provided
            this.feature = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
        }

        return new Config(this);
    }


    @Override
    public String toString() {
        return "Config{" +
                "kafkaClientId='" + kafkaClientId + '\'' +
                ", kafkaGroupId='" + kafkaGroupId + '\'' +
                ", kafkaAutoOffsetReset='" + kafkaAutoOffsetReset + '\'' +
                ", kafkaCommitIntervalMs=" + kafkaCommitIntervalMs +
                ", enableKafkaSSL='" + enableKafkaSSL + '\'' +
                ", kafkaBootstrapServers='" + kafkaBootstrapServers + '\'' +
                ", imageExtractionMetadataTopic='" + imageExtractionMetadataTopic + '\'' +
                ", extractedDocumentTopic='" + extractedDocumentTopic + '\'' +
                ", googleCredPath='" + googleCredPath + '\'' +
                ", runGVInPararell='" + runGVInParallel + '\'' +
                // Add other properties as needed
                '}';
    }
}

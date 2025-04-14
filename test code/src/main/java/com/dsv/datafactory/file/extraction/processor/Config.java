package com.dsv.datafactory.file.extraction.processor;

import com.google.cloud.vision.v1.Feature;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

//TODO: Check if this should be final params, and also if it is a better option to change it to private. Group Kafka params in another class?
@Getter
@Setter
public class Config {

    // Kafka configuration
    private final String kafkaClientId;
    private final String kafkaGroupId;
    private final String kafkaAutoOffsetReset;
    private final int kafkaCommitIntervalMs;
    private final String enableKafkaSSL;
    private final String kafkaBootstrapServers;
    private final String kafkaMaxRequestSize;
    private final String kafkaTruststorePath;
    private final String kafkaTruststoreFile;
    private final String kafkaTruststorePassword;
    private final String kafkaSLLProtocol;
    private final String kafkaSSLCipher;
    private final int kafkaPollIntervalMs;
    private final int kafkaRequestTimeoutMs;
    private final String enableRBAC;

    private final String imageExtractionMetadataTopic;
    private final String extractedDocumentTopic;
    private final Feature feature;
    private final String googleCredPath;
    //TODO: Parallel
    private final String runGVInParallel;
    private final String lineServiceUrl;
    private final String startNumberOfClasses;
    private final String goodnessOfFit;

    private Config(Builder builder) {
        // Kafka configuration
        this.kafkaClientId = builder.kafkaClientId;
        this.kafkaGroupId = builder.kafkaGroupId;
        this.kafkaAutoOffsetReset = builder.kafkaAutoOffsetReset;
        this.kafkaCommitIntervalMs = builder.kafkaCommitIntervalMs;
        this.enableKafkaSSL = builder.enableKafkaSSL;
        this.kafkaBootstrapServers = builder.kafkaBootstrapServers;
        this.kafkaMaxRequestSize = builder.kafkaMaxRequestSize;
        this.kafkaTruststorePath = builder.kafkaTruststorePath;
        this.kafkaTruststoreFile = builder.kafkaTruststoreFile;
        this.kafkaTruststorePassword = builder.kafkaTruststorePassword;
        this.kafkaSLLProtocol = builder.kafkaSLLProtocol;
        this.kafkaSSLCipher = builder.kafkaSSLCipher;
        this.kafkaPollIntervalMs = builder.kafkaPollIntervalMs;
        this.kafkaRequestTimeoutMs = builder.kafkaRequestTimeoutMs;
        this.enableRBAC = builder.enableRBAC;

        this.imageExtractionMetadataTopic = builder.imageExtractionMetadataTopic;
        this.extractedDocumentTopic = builder.extractedDocumentTopic;
        this.feature = builder.feature;
        this.googleCredPath = builder.googleCredPath;
        this.runGVInParallel = builder.runGVInPararell;
        this.lineServiceUrl = builder.lineServiceUrl;
        this.startNumberOfClasses = builder.startNumberOfClasses;
        this.goodnessOfFit = builder.goodnessOfFit;
    }
}

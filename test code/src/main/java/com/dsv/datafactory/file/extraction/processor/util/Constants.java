package com.dsv.datafactory.file.extraction.processor.util;

public enum Constants {

    LATEST("latest"),
    TRUE("true"),
    FALSE("false"),
    KAFKA_BOOT_STRAP_SERVERS_LOCALHOST("localhost:9092"),
    KAFKA_MAX_REQUEST_SIZE("kafkaMaxRequestSize"),
    KAFKA_SLL_PROTOCOL("kafkaSLLProtocol"),
    KAFKA_SSL_CIPHER("kafkaSSLCipher"),
    GOOGLE_CRED_PATH("credentials/google-cred.json"),
    LINE_SERVICE_URL("\"http://aif-jenks:8005/jenks/clustering\""),
    GOODNESS_OF_FIT("0.999")
    ;

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

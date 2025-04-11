package com.dsv.datafactory.file.extraction.processor;

import com.dsv.datafactory.file.extraction.processor.domain.ExtractContent;
import com.dsv.datafactory.model.MetaData;
import com.dsv.datafactory.serde.MetaDataSerde;
import com.google.inject.Inject;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class ExtractionStream {

    private Config config;

    private ExtractContent extractDocument;

    @Inject
    public ExtractionStream(Config config, ExtractContent extractDocument) {
        this.config = config;
        this.extractDocument = extractDocument;
    }

    public void createFrom(StreamsBuilder builder) {


        KStream<String, MetaData> stream = builder.stream(config.imageExtractionMetadataTopic,
                Consumed.with(Serdes.String(), new MetaDataSerde()));


        KStream<String, MetaData> documentExtractions = stream.mapValues(extractDocument::execute);
        KStream<String, MetaData> documentExtractionsFiltered = documentExtractions.filter((k, v) -> v != null);
        documentExtractionsFiltered.to(config.extractedDocumentTopic, Produced.with(Serdes.String(), new MetaDataSerde()));
    }

}

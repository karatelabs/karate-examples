package io.karatelabs.examples.kafka;

import com.intuit.karate.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;

public class AvroUtils {

    public static Schema toSchema(String fileName) {
        Schema.Parser parser = new Schema.Parser();
        String schemaText = FileUtils.toString(new File(fileName));
        return parser.parse(schemaText);
    }

    public static String toJson(GenericRecord gr) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            DatumWriter<GenericRecord> writer = new GenericDatumWriter(gr.getSchema());
            JsonEncoder encoder = EncoderFactory.get().jsonEncoder(gr.getSchema(), outputStream);
            writer.write(gr, encoder);
            encoder.flush();
            return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static GenericRecord fromJson(Schema schema, String json) {
        try {
            DatumReader reader = new GenericDatumReader(schema);
            return (GenericRecord) reader.read(null, DecoderFactory.get().jsonDecoder(schema, json));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

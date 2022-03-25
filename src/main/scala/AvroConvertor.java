import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class AvroConvertor {
    private final static String SCHEMA = "C:/Users/igm/Desktop/ExamVaccin/PersonAvroSchema.avsc";

    public byte[] generate(Person person) throws IOException {
        Schema.Parser parser = new Schema.Parser();

        String sc = null;

        FileInputStream inputStream = new FileInputStream(SCHEMA);
        try {
            sc = IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        Schema schema = parser.parse(sc);

        GenericData.Record avroRecord = new GenericData.Record(schema);
        var value = person.apply(schema);

        return GenericAvroCodecs.toBinary(schema).apply(value);
    }
}

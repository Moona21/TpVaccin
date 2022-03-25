import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerK {

    public void Consume(){
        try {
            Start();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void Start() throws Exception{
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("auto.offset.reset", "earliest");
        props.put("group.id","mygroup");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe((Arrays.asList("topicTP1")));

        System.out.println(("consmued from TopicTP1"));

        boolean running = true ;
        ObjectMapper jsonMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        while ( running){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for(ConsumerRecord<String,String> record : records){
                Person prn = jsonMapper.readValue(record.value(), Person.class);
                System.out.println(prn);


            }
        }

        consumer.close();



    }
    public void startAvro(int size){

        Properties properties =  new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.put("auto.offset.reset","earliest");
        properties.put("group.id","mygroup");

        KafkaConsumer<String, byte[]> consumer  = new KafkaConsumer<String, byte[]>(properties);
        consumer.subscribe(Arrays.asList("topicTP1"));
        System.out.println("consumed from topicTP1");

        boolean running = true;
        while (running){
            ConsumerRecords<String, byte[]> records = consumer.poll(size);
            records.forEach(record -> {
                System.out.println(record.value());
            });

        }

        consumer.close();

    }
}

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerK {
    Properties props = new Properties();
  KafkaProducer<String, String> producer;

    public void Produire(String prsnJson){
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic", prsnJson);
        producer.send(record);
        System.out.println("produced.");
        try{
            Thread.sleep(250);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void Init(){

        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

       producer = new KafkaProducer<String, String>(props);

    }

    public  void close(){

        producer.close();

    }


    public static void main(String[] args) {
       /* Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

       // JsonGenerator jg = new JsonGenerator();
        for(int i = 0; i < 20; i++){
            System.out.println("producing "+i);
            PersonGenerator pg = new PersonGenerator();
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic", jg.generate(pg));
            producer.send(record);
            System.out.println("produced.");
            try{
                Thread.sleep(250);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        producer.close();*/
    }
}

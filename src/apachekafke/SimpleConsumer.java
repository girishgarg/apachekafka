package apachekafke;
import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;



public class SimpleConsumer {
   public static void main(String[] args){
    //  if(args.length == 0){
      //   System.out.println("Enter topic name");
       //  return;
     // }
      //Kafka consumer configuration settings
      //String topicName = args[0].toString();
      Properties props = new Properties();
      
      props.put("bootstrap.servers", "localhost:9093");
      props.put("group.id", "UUID.randomUUID().toString()");
      props.put("enable.auto.commit", "true");
      props.put("auto.commit.interval.ms", "1000");
      props.put("session.timeout.ms", "30000");
      props.put("auto.offset.reset", "earliest");
      props.put("key.deserializer", 
         "org.apache.kafka.common.serialization.StringDeserializer");
      props.put("value.deserializer", 
        "org.apache.kafka.common.serialization.StringDeserializer");
      KafkaConsumer<String, String> consumer = new KafkaConsumer
         <String, String>(props);
      
      //Kafka Consumer subscribes list of topics here.
      consumer.subscribe(Arrays.asList("brokertest"));
      //consumer.seekToBeginning(consumer.assignment());
      //print the topic name
      System.out.println("Subscribed to topic " + "brokertest");
      int i = 0;
      
      while (true) {
         ConsumerRecords<String, String> records = consumer.poll(100);
         for (ConsumerRecord<String, String> record : records)
         
         // print the offset,key and value for the consumer records.
         System.out.printf("offset = %d, key = %s, value = %s\n", 
            record.offset(), record.key(), record.value());
      }
   }
}

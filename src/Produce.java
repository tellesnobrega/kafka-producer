/**
 * Created by tellesnobrega on 01/08/14.
 */

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;


public class Produce {

    public static void main(String[] args) {

        long messagesPerSecond = Long.parseLong(args[0]);
        Random rnd = new Random();

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "SimplePartitioner");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<String, String>(config);

        for (long nEvents = 0; nEvents < messagesPerSecond; nEvents++) {
            Integer key = rnd.nextInt(10);
            Integer value = rnd.nextInt(100);
            try {
                Thread.sleep(1000 / 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long runtime = new Date().getTime();
            String msg = runtime + ";" + value;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("consumptions", String.valueOf(key), msg);
            producer.send(data);
        }
        producer.close();
    }
}

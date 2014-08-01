/**
 * Created by tellesnobrega on 01/08/14.
 */

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.rmi.CORBA.Tie;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Produce {

    private static final Integer CONVERT_TO_MINUTES = 60000;
    private static final Logger log = LoggerFactory.getLogger(Produce.class);

    public static void main(String[] args) {

        long time = 1;
        Random rnd = new Random();

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "SimplePartitioner");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<String, String>(config);

        Date start = new GregorianCalendar().getTime();
        Date current = new GregorianCalendar().getTime();

        while(current.getTime() - start.getTime() < time * CONVERT_TO_MINUTES) {
            Integer key = rnd.nextInt(10);
            Integer value = rnd.nextInt(100);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Date runtime = new GregorianCalendar().getTime();
            String msg = runtime.toString() + ";" + value;

            log.info(value);
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("consumptions", String.valueOf(key), msg);
            producer.send(data);
            producer.close();
        }
    }
}

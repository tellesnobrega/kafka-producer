/**
 * Created by tellesnobrega on 01/08/14.
 */
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {

    public SimplePartitioner (VerifiableProperties props) {

    }

    @Override
    public int partition(Object o, int i) {
        Integer key = Integer.parseInt((String) o);
        return key%i;
    }
}

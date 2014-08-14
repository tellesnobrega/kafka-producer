#!/bin/bash
MAX=$1

for i in $(eval echo{1..$MAX})
do
    nohup java -jar /local/ubuntu/kafka-producer/target/kafka-producer.jar 4 &
done

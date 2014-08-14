#!/bin/bash


for i in {1..5}
do
    nohup java -jar target/kafka-producer.jar 2 &
done

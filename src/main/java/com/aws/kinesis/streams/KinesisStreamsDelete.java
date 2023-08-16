package com.aws.kinesis.streams;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.aws.kinesis.streaming.KinesisClient;

public class KinesisStreamsDelete extends KinesisClient {
    private final String streamName;
    public KinesisStreamsDelete(String streamName){
        this.streamName = streamName;
    }
    public void deleteStream(){
        AmazonKinesis amazonKinesis = getKinesisClient();
        amazonKinesis.deleteStream(this.streamName);
    }

    public static void main(String[] args) {
        KinesisStreamsDelete delete = new KinesisStreamsDelete("deliveries");
        delete.deleteStream();
    }
}

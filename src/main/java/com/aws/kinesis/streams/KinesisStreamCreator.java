package com.aws.kinesis.streams;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.CreateStreamResult;
import com.aws.kinesis.streaming.KinesisClient;

import java.util.Map;

public class KinesisStreamCreator extends KinesisClient{
    private final String streamName;
    public KinesisStreamCreator(String streamName){
        this.streamName = streamName;
    }
    public void createStreams(){
        KinesisStreams streams = new KinesisStreams();
        Map<String,Configuration> streamConfiguration = streams.getStreamConfiguration();
        AmazonKinesis amazonKinesis = getKinesisClient();
        if(this.streamName == null){
            System.out.println("Creating all the streams present");
            for(int streamCount =0 ; streamCount < streamConfiguration.size(); streamCount ++){
                Configuration conf = streamConfiguration.get(streamCount);
                CreateStreamResult result =amazonKinesis.createStream(conf.getStreamName(), conf.getStreamShardCount());
                System.out.println(result);
            }
        } else{
            System.out.println(String.format("Creating a stream for %s", this.streamName));
            Configuration conf = streamConfiguration.get(this.streamName);
            System.out.println(conf.toString());
            amazonKinesis.createStream(conf.getStreamName(), conf.getStreamShardCount());
        }
    }

    public static void main(String[] args) {
        KinesisStreamCreator creator = new KinesisStreamCreator("deliveries");
        creator.createStreams();
    }
}

package com.aws.kinesis.streaming;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.kinesis.model.PutRecordsResultEntry;
import com.aws.io.FileReader;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class KinesisProducer extends KinesisClient{
    private String streamName;
    public KinesisProducer(){
    }
    public KinesisProducer(String streamName){
        this.streamName = streamName;
    }
    public void pushRecordsToStream(ArrayList<String> lines){
        PutRecordsRequest putRecordRequest = new PutRecordsRequest();
        putRecordRequest.setStreamName(this.streamName);
        ArrayList<PutRecordsRequestEntry> recordEntry = new ArrayList<>();
        for(int i=0; i< lines.size(); i++){
            PutRecordsRequestEntry recordsRequestEntry = new PutRecordsRequestEntry();
            recordsRequestEntry.setData(ByteBuffer.wrap(lines.get(i).getBytes()));
            recordsRequestEntry.setPartitionKey(String.format("partition-%d", i%2));
            recordEntry.add(recordsRequestEntry);
        }
        putRecordRequest.setRecords(recordEntry);
        AmazonKinesis client = getKinesisClient();
        PutRecordsResult result = client.putRecords(putRecordRequest);
        for(PutRecordsResultEntry i: result.getRecords()){
            System.out.println(i);
        }

    }


    public static void main(String[] args) throws IOException {
        KinesisProducer producer = new KinesisProducer("deliveries");
        FileReader reader = new FileReader();
        ArrayList<String> lines = reader.readLines(Integer.parseInt(args[0]));
        producer.pushRecordsToStream(lines);
    }
}

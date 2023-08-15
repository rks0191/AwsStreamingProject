package com.aws.kinesis.streams;

import com.aws.io.FileReader;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
class Configuration{
private String streamName;
private int streamShardCount;

    public int getStreamShardCount() {
        return streamShardCount;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public void setStreamShardCount(int streamShardCount) {
        this.streamShardCount = streamShardCount;
    }
}
public class KinesisStreams {
    public Map<String, Configuration> getStreamConfiguration(){
        HashMap<String,Configuration> streamMap = new HashMap<>();
        FileReader reader = new FileReader();
        String readerConfiguration = reader.readStreamConfiguration();
        try{
            Gson json = new Gson();
            ArrayList <Configuration>conf = json.fromJson(readerConfiguration, ArrayList.class);
            for(Configuration configuration: conf){
                streamMap.put(configuration.getStreamName(),configuration);
            }
        } catch(Exception e){
            System.out.println("Invalid Json");
        }
        return streamMap;
    }
}

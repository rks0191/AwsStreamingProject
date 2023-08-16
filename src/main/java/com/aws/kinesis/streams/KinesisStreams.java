package com.aws.kinesis.streams;

import com.aws.io.FileReader;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
class Configuration{
private final String streamName;
private final int numberOfShards;

    public int getStreamShardCount() {
        return this.numberOfShards;
    }

    public String getStreamName() {
        return this.streamName;
    }
    public Configuration(String streamName, int streamShardCount){
        this.streamName = streamName;
        this.numberOfShards = streamShardCount;
    }

    @Override
    public String toString() {
        return String.format("Configuration(%s, %d)", this.streamName, this.numberOfShards);
    }
}
public class KinesisStreams {
    public Map<String, Configuration> getStreamConfiguration(){
        HashMap<String,Configuration> streamMap = new HashMap<>();
        FileReader reader = new FileReader();
        String readerConfiguration = reader.readStreamConfiguration();
        try{
            Gson json = new Gson();
            ArrayList <Object>conf = json.fromJson(readerConfiguration, ArrayList.class);
            for (Object object: conf){
                Configuration configuration = json.fromJson(String.valueOf(object), Configuration.class);
                streamMap.put(configuration.getStreamName(), configuration);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return streamMap;
    }
}

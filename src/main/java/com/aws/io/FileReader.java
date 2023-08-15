package com.aws.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

enum FileType{
    DELIVERIES,
    MATCHES
}
public class FileReader {
    private Enum fType;
    public ArrayList<String> readLines(int numberOfLines) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("deliveries.csv")));
        ArrayList<String> content = new ArrayList<>();
        for(int line=0; line <numberOfLines; line++){
            content.add(reader.readLine());
        }
        reader.close();
        return content;
    }
    public String readStreamConfiguration() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("streams.json")));
        StringBuffer buffer = new StringBuffer();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }
        catch (IOException e){
            System.out.println("Unable to read the file");
        }
        return buffer.toString();
    }
}

package com.aws.credentials;


public class Credentials {
    private String credentialsFilePath;
    private String region;
    public  Credentials(String credentialsFilePath){
        this.credentialsFilePath = credentialsFilePath;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
}

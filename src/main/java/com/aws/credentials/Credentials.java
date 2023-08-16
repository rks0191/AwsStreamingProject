package com.aws.credentials;


public class Credentials {
    private String region="us-east-1";
    public  Credentials(){

    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
}

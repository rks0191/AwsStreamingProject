package com.aws.kinesis.streaming;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.aws.credentials.Credentials;

public abstract class KinesisClient {
    public AmazonKinesis getKinesisClient(){
        Credentials credentials = new Credentials();
        AmazonKinesis builder = AmazonKinesisClientBuilder.standard().withRegion(credentials.getRegion()).build();
        return builder;
    }
}

**AWS streaming Project**
This contains code to
* Create a Kinesis Steam
* Push the records to the created Kinesis Streams
* Code to delete the kinesis Stream

**Purpose**
* Run a SDK to push the records to Kinesis Streams
* Use a lambda function to convert the data from CSV to Parquet and save the Same in s3
* Create a athena query to find the Most valuable player/IPL season
* Use quicksight to analyse the same
package com.cloud.ft06.user;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Map;

@Repository
public class UserDAO {

    //TODO change hardcoded values
    static BasicAWSCredentials credentials =  new BasicAWSCredentials("AKIAIKZBBPQLOQHT7GYQ", "l4lkusSDWVcB7n7k/vAOkgeLFgX7G6Ok+O0fPoi0");

    //AWSCredentialsProvider cp = new AWSStaticCredentialsProvider(BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));

    // AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials).withRegion(Regions.US_WEST_2);
    //  DynamoDB dynamoDB = new DynamoDB(client);

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)
    ).withRegion(Regions.US_WEST_2).build();

    public Map<String, Object> addUser(Map<String, Object> userDetails) {
        User user = new User();
        user.setId((Integer)userDetails.get("id"));
        user.setFirstName((String)userDetails.get("firstName"));
        user.setLastName((String)userDetails.get("lastName"));

        // Save the item (book).
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(user);
       // User user =
        return userDetails;
    }

    public String getUser(Integer pId){
        DynamoDB dynamoDB = new DynamoDB(client);
        Table userTable = dynamoDB.getTable("User");
        QuerySpec query = new QuerySpec()
                .withKeyConditionExpression("id = :v_id")
                .withValueMap(new ValueMap()
                        .withInt(":v_id", pId));

        ItemCollection<QueryOutcome> items = userTable.query(query);
        Iterator<Item> iterator = items.iterator();
        Item item = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            break;
        }

        return item.toJSONPretty();


    }
}

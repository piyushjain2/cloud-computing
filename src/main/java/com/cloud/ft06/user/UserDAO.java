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

import java.util.*;


@Repository
public class UserDAO {

    //TODO change hardcoded values
    static BasicAWSCredentials credentials =  new BasicAWSCredentials("AKIAIKZBBPQLOQHT7GYQ", "l4lkusSDWVcB7n7k/vAOkgeLFgX7G6Ok+O0fPoi0");

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)
    ).withRegion(Regions.US_WEST_2).build();

    static DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);


    /*
        Adding user to dynamoDB
     */
    public Map<String, Object> addUser(Map<String, Object> userDetails) {
        User user = new User();
        user.setId((Integer)userDetails.get("id"));
        user.setFirstName((String)userDetails.get("firstName"));
        user.setLastName((String)userDetails.get("lastName"));
        Follow follow = new Follow();
        follow.setId((Integer)userDetails.get("id"));
        follow.setFollowing(new ArrayList<Integer>());
        follow.setFollowers(new ArrayList<Integer>());
        dynamoDBMapper.save(user);
        dynamoDBMapper.save(follow);
        return userDetails;
    }


    /*
        Getting user from db using Query
     */
    public String getUserWithQuery(Integer pId){
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


    /*
        Getting user using mapper
     */
    public User getUser(Integer pId){
        User user = dynamoDBMapper.load(User.class,pId);
        return user;
    }


    /*
        Updating user in DB
     */
    public Map<String, Object> updateUser(Map<String, Object> userDetails){
        User user = dynamoDBMapper.load(User.class,userDetails.get("id"));
        user.setFirstName((String)userDetails.get("firstName"));
        user.setLastName((String)userDetails.get("lastName"));
        dynamoDBMapper.save(user);
        return userDetails;
    }


    /*
       Getting Follow using mapper
    */
    public Follow getFollow(Integer pId){
        Follow follow = dynamoDBMapper.load(Follow.class,pId);
        return follow;
    }


    /*
        Adding follower to dynamoDB
     */
    public Map<String, Integer> addUserToFollowers(Map<String, Integer> followDetails) {
        Follow follow = dynamoDBMapper.load(Follow.class,followDetails.get("id"));
        follow.getFollowers().add(followDetails.get("followersId"));
        dynamoDBMapper.save(follow);
        return followDetails;
    }


    /*
        Adding follower to dynamoDB
     */
    public Map<String, Integer> addUserToFollowing(Map<String, Integer> followDetails) {
        Follow follow = dynamoDBMapper.load(Follow.class,followDetails.get("id"));
        if(follow.getFollowing() != null) {
            follow.getFollowing().add(followDetails.get("followingId"));
        }else {
            List<Integer> followingId = new ArrayList<Integer>();
            followingId.add(followDetails.get("followingId"));
            follow.setFollowing(followingId);
        }
        dynamoDBMapper.save(follow);
        return followDetails;
    }
}

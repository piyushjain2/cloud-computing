package com.cloud.ft06.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;


@DynamoDBTable(tableName = "Follow")
public class Follow {

    private Integer id;
    private List<Integer> followers;
    private List<Integer> following;


    // Partition key
    @DynamoDBHashKey(attributeName = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "followers")
    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    @DynamoDBAttribute(attributeName = "following")
    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "FollowList [Followers=" + followers + ", Following=" + following + ", userId=" + id +"]";
    }
}

package com.cloud.ft06.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;


    @DynamoDBTable(tableName = "User")
    public class User {
        private Integer id;
        private String firstName;
        private String lastName;


        // Partition key
        @DynamoDBHashKey(attributeName = "id")
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @DynamoDBAttribute(attributeName = "firstName")
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @DynamoDBAttribute(attributeName = "lastName")
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }



        @Override
        public String toString() {
            return "Book [FirstName=" + firstName + ", LastName=" + lastName + ", id=" + id +"]";
        }
    }



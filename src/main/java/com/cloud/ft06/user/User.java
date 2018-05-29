package com.cloud.ft06.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


    @DynamoDBTable(tableName = "User")
    public class User {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String username;


        // Partition key
        @DynamoDBHashKey(attributeName = "username")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        @DynamoDBAttribute(attributeName = "email")
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @DynamoDBAttribute(attributeName = "password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Book [FirstName=" + firstName + ", LastName=" + lastName + ", id=" + id +"]";
        }
    }



package com.cloud.ft06.user;


import com.cloud.ft06.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


   @Autowired
    private UserDAO userDAO;
    // private DynamoDBMapperCRUDExample dynamo;

    @PostMapping("/addUser")
    public Map<String, Object> addUser(@RequestBody Map<String, Object> userDetails) {
        return userDAO.addUser(userDetails);
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam(value = "id") Integer pId){
        return userDAO.getUser(pId);
    }
}

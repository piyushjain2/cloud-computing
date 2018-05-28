package com.cloud.ft06.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


   @Autowired
    private UserDAO userDAO;


    @PostMapping("/addUser")
    public Map<String, Object> addUser(@RequestBody Map<String, Object> userDetails) {
        return userDAO.addUser(userDetails);
    }


    @GetMapping("/getUser")
    public User getUser(@RequestParam(value = "id") Integer pId){
        return userDAO.getUser(pId);
    }


    @PostMapping("/updateUser")
    public Map<String, Object> updateUser(@RequestBody Map<String, Object> userDetails){
        return userDAO.updateUser(userDetails);
    }


    @GetMapping("/getFollow")
    public Follow getFollow(@RequestParam(value = "userId") Integer pId){
        return userDAO.getFollow(pId);
    }


    @PostMapping("/addUserToFollowers")
    public Map<String, Integer> addUserToFollowers(@RequestBody Map<String, Integer> followDetails){
        return userDAO.addUserToFollowers(followDetails);
    }


    @PostMapping("/addUserToFollowing")
    public Map<String, Integer> addUserToFollowing(@RequestBody Map<String, Integer> followDetails){
        return userDAO.addUserToFollowing(followDetails);
    }


}

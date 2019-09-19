package com.example.demo.service;

import com.example.demo.soap.User;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static final Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void initialize() {
        User peter = new User();
        peter.setName("Peter");
        peter.setEmpId(1);
        peter.setSalary(30000);

        User jack = new User();
        jack.setName("Jack");
        jack.setEmpId(2);
        jack.setSalary(50000);

        users.put(peter.getName(), peter);
        users.put(jack.getName(), jack);
    }

    public User getUser(String name) {
        return users.get(name);
    }
}

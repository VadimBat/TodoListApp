package com.goit.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {

        SpringApplication.run(TodolistApplication.class, args);

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encoded = passwordEncoder.encode("jdbcDefault");
//        System.out.println("encoded = " + encoded);
    }

}

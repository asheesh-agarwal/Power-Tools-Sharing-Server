package com.asheesh.cs5356.pts.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.asheesh.cs5356.pts.repository")
@ComponentScan(basePackages="com.asheesh.cs5356.pts")
@EntityScan(basePackages="com.asheesh.cs5356.pts.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
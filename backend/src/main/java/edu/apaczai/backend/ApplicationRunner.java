package edu.apaczai.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {
    @Autowired

    public static void main(String[] args) {

        SpringApplication.run(ApplicationRunner.class, args);
    }

}

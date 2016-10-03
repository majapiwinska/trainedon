package org.example;

import groovy.lang.Grab;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Grab("org.webjars:jquery:2.0.3-1")
@SpringBootApplication
@EnableTransactionManagement
public class Application
{
    public static void main( String[] args ){
        SpringApplication.run(Application.class, args);
    }
}

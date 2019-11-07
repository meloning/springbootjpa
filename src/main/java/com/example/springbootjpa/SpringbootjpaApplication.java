package com.example.springbootjpa;

import com.example.springbootjpa.bean_definition.MemberRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MemberRegistrar.class)
public class SpringbootjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjpaApplication.class, args);
    }

}

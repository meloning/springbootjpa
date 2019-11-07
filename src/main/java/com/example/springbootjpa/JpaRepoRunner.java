package com.example.springbootjpa;

import com.example.springbootjpa.bean_definition.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

//@Component
//@EnableJpaRepositories
public class JpaRepoRunner implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

//    @Autowired
//    Member member;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(member.getName());


    }
}

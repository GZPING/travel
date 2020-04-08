package com.gd.travel;

import com.gd.travel.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Admin
 */
@SpringBootApplication
@MapperScan("com.gd.travel.mapper")
public class TravelApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TravelApplication.class, args);
        Object user = run.getBean("getUser");
        System.out.println(user);
  /*      ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationfile.xml");
        System.out.println(context.getBean("user"));*/
    }


    @Bean
    public User getUser(){
        User user = new User();
        user.setAge(321);
        user.setName("name");
        return user;
    }

}

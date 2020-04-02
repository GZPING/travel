package com.gd.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Admin
 */
@SpringBootApplication
@MapperScan("com.gd.travel.mapper")
public class TravelApplication {

    public static void main(String[] args) {
       /* SpringApplication.run(TravelApplication.class, args);*/
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationfile.xml");
        System.out.println(context.getBean("user"));

    }


}

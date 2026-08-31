package com.rookies6.myspringboot4project.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyRunner implements ApplicationRunner {
    @Value("${spring.application.name}")
    String applicationName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyRunner run() 호출됨!!");
        System.out.println("Application Name = " + applicationName);

        // Consumer 인터페이스를 Anonymous Inner Class
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("arg name = " + s);
            }
        });

        // args.getOptionNames() 메서드의 리턴타입 Set<String>
        // Iterable의 forEach(Consumer)
        // Consumer의 추상메서드 void accept(T t)
        // Consumer 인터페이스를 람다식으로 표현
        args.getOptionNames().forEach(name -> System.out.println("name = " + name));
    }
}
package demo.pkg.helloapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("demo.pkg.helloapp.dao")
public class HelloappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloappApplication.class, args);
    }

}

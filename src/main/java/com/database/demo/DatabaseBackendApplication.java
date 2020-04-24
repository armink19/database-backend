package com.database.demo;

import com.database.demo.repository.SampleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EntityScan("com.database.demo.model")
//@ComponentScan({"com.database.demo.controller"})
@EnableJpaRepositories(basePackageClasses ={ SampleRepository.class,JpaBaseConfiguration.class})



public class DatabaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseBackendApplication.class, args);
    }

}

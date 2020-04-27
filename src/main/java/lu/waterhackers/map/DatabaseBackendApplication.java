package lu.waterhackers.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan("com.database.demo.model")
//@ComponentScan({"com.database.demo.controller"})


public class DatabaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseBackendApplication.class, args);
    }

}

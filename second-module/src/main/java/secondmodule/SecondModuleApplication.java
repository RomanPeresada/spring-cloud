package secondmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SecondModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondModuleApplication.class, args);
    }

}

package oort.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "oort.cloud.shop")
@EntityScan("oort.cloud.shop.entity")
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class);
    }
}

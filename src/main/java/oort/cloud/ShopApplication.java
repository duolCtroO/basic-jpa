package oort.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "oort.cloud.shop")
@EntityScan("oort.cloud.shop")
@EnableJpaRepositories(
        basePackages = "oort.cloud.shop.repository",
        repositoryImplementationPostfix = "CustomImpl" // 기본 "Impl" 대신 커스텀 접미사 사용
)
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class);
    }
}

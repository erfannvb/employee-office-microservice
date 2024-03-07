package nvb.dev.officeservice;

import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.repository.OfficeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
public class OfficeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(OfficeRepository officeRepository) {
        return args -> officeRepository.saveAll(List.of(
                new OfficeEntity(UUID.randomUUID().toString(), "Microsoft", "1234"),
                new OfficeEntity(UUID.randomUUID().toString(), "Meta", "1498")
        ));
    }

}

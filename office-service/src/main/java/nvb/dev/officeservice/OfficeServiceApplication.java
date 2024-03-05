package nvb.dev.officeservice;

import nvb.dev.officeservice.dao.entity.OfficeEntity;
import nvb.dev.officeservice.repository.OfficeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class OfficeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner loadData(OfficeRepository officeRepository) {
        return args -> officeRepository.saveAll(List.of(
                new OfficeEntity(UUID.randomUUID().toString(), "Microsoft", "202-555-0152"),
                new OfficeEntity(UUID.randomUUID().toString(), "Meta", "202-555-0197")
        ));
    }

}

package nvb.dev.notificationservice;

import lombok.extern.slf4j.Slf4j;
import nvb.dev.notificationservice.event.CreateEmployeeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(CreateEmployeeEvent event) {
        log.info("Received Notification for Employee - {}", event.getFirstName() + " " + event.getLastName());
    }

}

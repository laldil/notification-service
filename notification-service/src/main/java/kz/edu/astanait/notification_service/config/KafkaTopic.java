package kz.edu.astanait.notification_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder.name("notification-topic")
                .partitions(3)
                .build();
    }
}
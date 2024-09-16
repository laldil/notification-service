package kz.edu.astanait.senderservice.config;

import kz.edu.astanait.senderservice.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaConsumerConfig {

    @Value("${settings.kafka.server}")
    private String kafkaServer;

    @Value("${settings.kafka.backoff.intervalMs}")
    private Long backoffInterval;

    @Value("${settings.kafka.backoff.attempts}")
    private Integer backoffAttempts;

    @Bean
    public ConsumerFactory<String, NotificationDto> notificationConsumerFactory() {
        var deserializer = new JsonDeserializer<>(NotificationDto.class);
        deserializer.addTrustedPackages("*");
        deserializer.ignoreTypeHeaders();

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        var fixedBackOff = new FixedBackOff(backoffInterval, backoffAttempts);
        var errorHandler = new DefaultErrorHandler(fixedBackOff);

        // TODO: Test
        errorHandler.setRetryListeners((record, e, attempt) ->
                log.warn("Retrying record {} due to exception {}, attempt {}", record, e.getMessage(), attempt));

        return errorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationDto> notificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationConsumerFactory());
        factory.setCommonErrorHandler(errorHandler());
        factory.setConcurrency(3);
        return factory;
    }
}

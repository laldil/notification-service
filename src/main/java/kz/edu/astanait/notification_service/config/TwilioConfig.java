package kz.edu.astanait.notification_service.config;

import com.twilio.Twilio;
import kz.edu.astanait.notification_service.config.properties.TwilioProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwilioConfig {

    @Autowired
    public TwilioConfig(TwilioProperties twilioProperties) {
        Twilio.init(twilioProperties.getAccountSid(), twilioProperties.getAuthToken());
        log.info("Twilio initialized. Account SID: %s".formatted(twilioProperties.getAccountSid()));
    }
}

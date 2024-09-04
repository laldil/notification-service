package kz.edu.astanait.notification_service.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "settings.twilio")
public class TwilioProperties {
    private String accountSid;
    private String authToken;
    private String number;
    private String whatsappNumber;
}

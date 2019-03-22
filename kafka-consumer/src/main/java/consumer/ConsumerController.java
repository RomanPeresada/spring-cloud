package consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class ConsumerController {

    @KafkaListener(topics = "news", groupId = "first")
    public String getMessage(String in,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("message: " + in + ", topic: " + topic);
        return in;
    }
}

package firstmodule;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import firstmodule.domain.CommandDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FirstService {
    @Autowired
    private FeignFirst feignFirst;

    @Autowired
    private KafkaTemplate<String, CommandDomain> kafkaTemplate;

    @HystrixCommand(fallbackMethod = "defaultMethod")
    public String getSecond() {
        return feignFirst.getSecond();
    }

    private String defaultMethod() {
        return "defaultMethod";
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    public void produce() {
        kafkaTemplate.send("show", CommandDomain.builder()
                .id((long) new Random().nextInt(2411))
                .name("name + " + System.currentTimeMillis() / 10000)
                .password("password " + +System.currentTimeMillis() / 10000)
                .yearOfBirthday(new Random().nextInt(2311))
                .build());
    }
}

package firstmodule.controller;

import firstmodule.domain.CommandDomain;
import firstmodule.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping(value = "/command")
public class CommandController {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private KafkaTemplate<String, CommandDomain> kafkaTemplate;

    @PostMapping(value = "/add")
    public void createEntity() {
        Random random = new Random();
        CommandDomain commandDomain = CommandDomain.builder()
                .login(UUID.randomUUID().toString())
                .password("password" + random.nextInt(2000))
                .yearOfBirthday(random.nextInt(60) + 1940)
                .name(UUID.randomUUID().toString().substring(0, 7))
                .build();
        commandDomain = commandRepository.save(commandDomain);
        this.kafkaTemplate.send("add", commandDomain);
    }

}

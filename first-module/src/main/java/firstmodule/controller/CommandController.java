package firstmodule.controller;

import firstmodule.domain.CommandDomain;
import firstmodule.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/add")
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

//    @GetMapping(value = "delete/{id}")
//    public void delete(@PathVariable Long id) {
//        ListenableFuture<SendResult<String, CommandDomain>> delete = kafkaTemplate.send("delete", commandRepository.getOne(id));
//        delete.addCallback(new ListenableFutureCallback<SendResult<String, CommandDomain>>() {
//            @Override
//            public void onSuccess(SendResult<String, CommandDomain> stringCommandDomainSendResult) {
//                commandRepository.deleteById(id);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                System.err.println("Error while was deleting");
//            }
//
//        });
//
//    }
}

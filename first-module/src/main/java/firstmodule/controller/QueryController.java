package firstmodule.controller;

import firstmodule.domain.CommandDomain;
import firstmodule.domain.QueryDomain;
import firstmodule.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/query")
public class QueryController {

    @Autowired
    private QueryRepository queryRepository;

    @GetMapping(value = "/get-all")
    public List<QueryDomain> getAll() {
        return queryRepository.findAll();
    }

    @GetMapping("/{id}")
    public QueryDomain getOneById(@PathVariable Long id) {
        return queryRepository.getOne(id);
    }

    @KafkaListener(topics = "add", groupId = "1")
    public void add(CommandDomain commandDomain) {
        queryRepository.save(QueryDomain.builder()
                .id(commandDomain.getId())
                .name(commandDomain.getName())
                .yearOfBirthday(commandDomain.getYearOfBirthday())
                .build());
        System.out.println("Success: " + commandDomain.toString());
    }

    @KafkaListener(topics = "show", groupId = "2")
    public void show(CommandDomain commandDomain) {
        System.out.println("Have got: " + commandDomain);
    }

//    @KafkaListener(topics = "delete", groupId = "1")
//    public void delete(CommandDomain commandDomain) {
//        queryRepository.delete(QueryDomain.builder()
//                .id(commandDomain.getId())
//                .name(commandDomain.getName())
//                .yearOfBirthday(commandDomain.getYearOfBirthday())
//                .build());
//        System.out.println("Success deleting: " + commandDomain);
//    }
}

package firstmodule.controller;

import firstmodule.FirstService;
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

    @Autowired
    private FirstService firstService;

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


    @GetMapping("/first")
    public String getMyString() {
        return "from first";
    }

    @GetMapping("/second-from-first")
    public String getSecond() {
        return firstService.getSecond();
    }

}

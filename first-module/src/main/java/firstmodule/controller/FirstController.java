package firstmodule.controller;

import firstmodule.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/first")
public class FirstController {

    @Autowired
    private FirstService firstService;

    @GetMapping
    public String getMyString() {
        return "from first";
    }

    @GetMapping("/second-from-first")
    public String getSecond() {
        return firstService.getSecond();
    }
}

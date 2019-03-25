package firstmodule;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "second-app")
public interface FeignFirst {
    @GetMapping("/second")
    String getSecond();
}

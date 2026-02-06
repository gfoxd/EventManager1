package boot.spring.eventmanager_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LocationController {

    @GetMapping
    public String getLocation() {
        return "Location";
    }

}

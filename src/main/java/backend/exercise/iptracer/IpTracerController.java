package backend.exercise.iptracer;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpTracerController {
    @RequestMapping(value = "/trace", method = RequestMethod.POST)
    public String trace(@RequestBody IpDataDto ipData) {
        return "Hello: " + ipData.getId();
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats() {
        return "HEllo world!";
    }
}

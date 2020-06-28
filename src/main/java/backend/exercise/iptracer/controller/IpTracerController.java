package backend.exercise.iptracer.controller;

import backend.exercise.iptracer.dto.IpDataDto;
import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationResponse;
import backend.exercise.iptracer.service.iptracer.IpTracerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class IpTracerController {

    IpTracerService ipTracerService;

    public IpTracerController(IpTracerService ipTracerService) {
        this.ipTracerService = ipTracerService;
    }

    @RequestMapping(value = "/trace", method = RequestMethod.POST)
    @ResponseBody
    public IpGeolocalizationResponse trace(@RequestBody IpDataDto ipData) {
        return ipTracerService.trace(ipData.getId());
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats() {
        return "HEllo world!";
    }
}

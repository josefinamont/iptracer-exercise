package backend.exercise.iptracer.controller;

import backend.exercise.iptracer.dtos.Distances;
import backend.exercise.iptracer.dtos.IpDataDto;
import backend.exercise.iptracer.dtos.IpTracerResponse;
import backend.exercise.iptracer.model.exceptions.EmptyResponseException;
import backend.exercise.iptracer.model.exceptions.InvalidFieldException;
import backend.exercise.iptracer.model.exceptions.InvalidIpFormatException;
import backend.exercise.iptracer.model.exceptions.UnexpectedResponseStatusException;
import backend.exercise.iptracer.service.iptracer.IpTracerService;
import backend.exercise.iptracer.service.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IpTracerController {
    @Autowired
    IpTracerService ipTracerService;
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/trace", method = RequestMethod.POST)
    @ResponseBody
    public IpTracerResponse trace(@RequestBody IpDataDto ipData) {
        return ipTracerService.trace(ipData.getId());
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @ResponseBody
    public Distances stats() {
        return statisticsService.distances();
    }
}

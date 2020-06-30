package backend.exercise.iptracer.controller;

import backend.exercise.iptracer.dto.IpDataDto;
import backend.exercise.iptracer.model.exceptions.EmptyResponseException;
import backend.exercise.iptracer.model.exceptions.InvalidFieldException;
import backend.exercise.iptracer.model.exceptions.InvalidIpFormatException;
import backend.exercise.iptracer.model.exceptions.UnexpectedResponseStatusException;
import backend.exercise.iptracer.service.statistics.StatisticsService;
import backend.exercise.iptracer.service.iptracer.DistancesResponse;
import backend.exercise.iptracer.service.iptracer.IpTracerResponse;
import backend.exercise.iptracer.service.iptracer.IpTracerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class IpTracerController {
    @Autowired
    IpTracerService ipTracerService;
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/trace", method = RequestMethod.POST)
    @ResponseBody
    public IpTracerResponse trace(@RequestBody IpDataDto ipData) {
        IpTracerResponse response = ipTracerService.trace(ipData.getId());
        statisticsService.updateStatistics(response);

        return response;
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @ResponseBody
    public DistancesResponse stats() {
        return statisticsService.distances();
    }

    @ExceptionHandler(value = InvalidIpFormatException.class)
    public ResponseEntity<Object> exception(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { UnexpectedResponseStatusException.class, InvalidFieldException.class, EmptyResponseException.class})
    public ResponseEntity<Object> exception(UnexpectedResponseStatusException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package backend.exercise.iptracer.controller;

import backend.exercise.iptracer.dto.IpDataDto;
import backend.exercise.iptracer.model.exceptions.EmptyResponseException;
import backend.exercise.iptracer.model.exceptions.InvalidFieldException;
import backend.exercise.iptracer.model.exceptions.InvalidIpException;
import backend.exercise.iptracer.model.exceptions.UnexpectedResponseStatusException;
import backend.exercise.iptracer.service.iptracer.IpTracerResponse;
import backend.exercise.iptracer.service.iptracer.IpTracerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class IpTracerController {

    IpTracerService ipTracerService;

    public IpTracerController(IpTracerService ipTracerService) {
        this.ipTracerService = ipTracerService;
    }

    @RequestMapping(value = "/trace", method = RequestMethod.POST)
    @ResponseBody
    public IpTracerResponse trace(@RequestBody IpDataDto ipData) {
        return ipTracerService.trace(ipData.getId());
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String stats() {
        return "HEllo world!";
    }

    @ExceptionHandler(value = InvalidIpException.class)
    public ResponseEntity<Object> exception(InvalidIpException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnexpectedResponseStatusException.class)
    public ResponseEntity<Object> exception(UnexpectedResponseStatusException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidFieldException.class)
    public ResponseEntity<Object> exception(InvalidFieldException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EmptyResponseException.class)
    public ResponseEntity<Object> exception(EmptyResponseException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package backend.exercise.iptracer.service.iptracer;

import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationResponse;
import backend.exercise.iptracer.service.geolocalization.IpGeolocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpTracerService {
    @Autowired
    private IpGeolocalizationService geolocalizationService;

    public IpGeolocalizationResponse trace(String ip) {
        return geolocalizationService.getIpGeolocalization(ip);
    }
}

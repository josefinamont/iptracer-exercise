package backend.exercise.iptracer.service.fixer;

import backend.exercise.iptracer.clients.FixerClient;
import backend.exercise.iptracer.dtos.FixerResponse;
import backend.exercise.iptracer.repository.FixerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FixerService {
    @Autowired
    private FixerClient fixerClient;
    @Autowired
    private FixerRepository fixerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FixerService.class);

    public Double getCurrencyRate(String currencyCode) {
        Optional<Double> possibleRates = fixerRepository.getRateForCurrency(currencyCode);

        if (!possibleRates.isPresent()) {
            LOGGER.info("Rate not found in local repository");
            FixerResponse fixerResponse = fixerClient.getResponse();

            LOGGER.info("Updating the local repository");
            fixerRepository.withRates(fixerResponse.getRates());

            fixerRepository.getRateForCurrency(currencyCode).ifPresent(rate ->
                    LOGGER.info("Found rate for currency code: " + currencyCode)
            );

            return fixerRepository.getRateForCurrency(currencyCode).<RuntimeException>orElseThrow(() -> {
                        String errorMsg = "Could not find the rate for the currency code: " + currencyCode;
                        LOGGER.info(errorMsg);
                        throw new RuntimeException(errorMsg);
                    }
            );
        } else
            LOGGER.info("Found rate in the repository for currency code: " + currencyCode);
        return possibleRates.get();
    }
}

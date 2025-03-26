package ee.ursulavisnapuu.lennuplaneerija.controller;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import ee.ursulavisnapuu.lennuplaneerija.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @GetMapping("/search")
    public List<Flight> searchByDestination(@RequestParam String destination) {
        return flightRepository.findByDestination(destination);
    }
}

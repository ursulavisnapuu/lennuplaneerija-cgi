package ee.ursulavisnapuu.lennuplaneerija.controller;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import ee.ursulavisnapuu.lennuplaneerija.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        return flightService.updateFlight(id, updatedFlight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    
    @GetMapping("/search-by-origin")
    public List<Flight> searchByOriginAndDate(@RequestParam String origin, @RequestParam String date) {
        return flightService.findByOriginAndDate(origin, date);
    }

    
    @GetMapping("/search/price")
    public List<Flight> searchFlightsByPriceRange(
            @RequestParam String destination,
            @RequestParam double minPrice,
            @RequestParam double maxPrice
    ) {
        return flightService.findByDestinationAndPriceRange(destination, minPrice, maxPrice);
    }
}

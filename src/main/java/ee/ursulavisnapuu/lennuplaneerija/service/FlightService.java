package ee.ursulavisnapuu.lennuplaneerija.service;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import ee.ursulavisnapuu.lennuplaneerija.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getFlightsByDestination(String destination) {
        return flightRepository.findByDestination(destination);
    }

    public List<Flight> findFlightsByOriginAndDate(String origin, String date) {
        return flightRepository.findByOriginAndDate(origin, date);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        Flight existing = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
        existing.setOrigin(updatedFlight.getOrigin());
        existing.setDestination(updatedFlight.getDestination());
        existing.setDate(updatedFlight.getDate());
        existing.setPrice(updatedFlight.getPrice());
        return flightRepository.save(existing);
    }
}

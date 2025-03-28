package ee.ursulavisnapuu.lennuplaneerija.service;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import ee.ursulavisnapuu.lennuplaneerija.model.Passenger;
import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.repository.FlightRepository;
import ee.ursulavisnapuu.lennuplaneerija.repository.PassengerRepository;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightRepository flightRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public List<Passenger> saveAllPassengers(List<Passenger> passengers) {
        return passengerRepository.saveAll(passengers);
    }

    public List<Passenger> assignFlightToPassengers(List<Long> passengerIds, Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));
    
        List<Passenger> passengers = passengerRepository.findAllById(passengerIds);
        for (Passenger passenger : passengers) {
            passenger.setFlight(flight);
        }
    
        return passengerRepository.saveAll(passengers);
    }

    public List<Passenger> assignFlightToPassengers(Long flightId, List<Long> passengerIds) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));
    
        List<Passenger> passengers = passengerRepository.findAllById(passengerIds);
    
        for (Passenger passenger : passengers) {
            passenger.setFlight(flight);
        }
    
        return passengerRepository.saveAll(passengers);
    }
    
    

    public void deletePassengerById(Long id) {
        passengerRepository.deleteById(id);
    }

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        Passenger existingPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));

        existingPassenger.setFirstName(updatedPassenger.getFirstName());
        existingPassenger.setLastName(updatedPassenger.getLastName());
        existingPassenger.setEmail(updatedPassenger.getEmail());
        existingPassenger.setSeat(updatedPassenger.getSeat());
        existingPassenger.setFlight(updatedPassenger.getFlight());

        return passengerRepository.save(existingPassenger);
    }

    public Passenger assignSeatToPassenger(Long passengerId, Long seatId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + passengerId));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + seatId));

        passenger.setSeat(seat);
        seat.setOccupied(true);

        seatRepository.save(seat);
        return passengerRepository.save(passenger);
    }

    public Passenger assignFlightToPassenger(Long passengerId, Long flightId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + passengerId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));

        passenger.setFlight(flight);
        return passengerRepository.save(passenger);
    }
}

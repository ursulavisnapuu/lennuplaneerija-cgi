package ee.ursulavisnapuu.lennuplaneerija.controller;

import ee.ursulavisnapuu.lennuplaneerija.model.Passenger;
import ee.ursulavisnapuu.lennuplaneerija.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ee.ursulavisnapuu.lennuplaneerija.dto.AssignFlightRequest;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @PostMapping
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    @PostMapping("/batch")
    public List<Passenger> createPassengers(@RequestBody List<Passenger> passengers) {
        return passengerService.saveAllPassengers(passengers);
    }

    @PostMapping("/assign-flight")
    public List<Passenger> assignFlightToPassengers(@RequestBody AssignFlightRequest request) {
    return passengerService.assignFlightToPassengers(request.getPassengerIds(), request.getFlightId());
}

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }

    @PutMapping("/{id}/assign-seat/{seatId}")
    public Passenger assignSeatToPassenger(@PathVariable Long id, @PathVariable Long seatId) {
        return passengerService.assignSeatToPassenger(id, seatId);
    }

    @PutMapping("/{id}/assign-flight/{flightId}")
    public Passenger assignFlightToPassenger(@PathVariable Long id, @PathVariable Long flightId) {
        return passengerService.assignFlightToPassenger(id, flightId);
    }

    @PutMapping("/assign-flight/{flightId}")
    public List<Passenger> assignFlightToPassengers(@PathVariable Long flightId, @RequestBody List<Long> passengerIds) {
    return passengerService.assignFlightToPassengers(flightId, passengerIds);
}

}

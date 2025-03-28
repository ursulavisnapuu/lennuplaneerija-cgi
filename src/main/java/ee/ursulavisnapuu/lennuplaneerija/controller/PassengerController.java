package ee.ursulavisnapuu.lennuplaneerija.controller;

import ee.ursulavisnapuu.lennuplaneerija.model.Passenger;
import ee.ursulavisnapuu.lennuplaneerija.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
    }

    @PostMapping("/batch")
public List<Passenger> createPassengers(@RequestBody List<Passenger> passengers) {
    return passengerService.saveAllPassengers(passengers);
}

}

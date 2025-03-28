package ee.ursulavisnapuu.lennuplaneerija.service;

import ee.ursulavisnapuu.lennuplaneerija.model.Passenger;
import ee.ursulavisnapuu.lennuplaneerija.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deletePassengerById(Long id) {
        passengerRepository.deleteById(id);
    }

    public List<Passenger> saveAllPassengers(List<Passenger> passengers) {
        return passengerRepository.saveAll(passengers);
    }
    
}

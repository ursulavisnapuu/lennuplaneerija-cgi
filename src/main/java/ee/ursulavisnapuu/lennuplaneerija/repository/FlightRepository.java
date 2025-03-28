package ee.ursulavisnapuu.lennuplaneerija.repository;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDestination(String destination);
    List<Flight> findByDestinationAndPriceBetween(String destination, double minPrice, double maxPrice);
    List<Flight> findByOriginAndDate(String origin, String date);
    

}



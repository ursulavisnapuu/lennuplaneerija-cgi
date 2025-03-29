package ee.ursulavisnapuu.lennuplaneerija.repository;

import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByOccupiedFalse(); // vabad istekohad

    List<Seat> findByFlightId(Long flightId); // istekohad konkreetsele lennule
}

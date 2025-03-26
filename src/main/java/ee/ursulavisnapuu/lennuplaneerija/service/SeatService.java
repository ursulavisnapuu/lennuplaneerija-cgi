package ee.ursulavisnapuu.lennuplaneerija.service;

import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> suggestSeat(boolean preferWindow, boolean preferLegroom, boolean preferExit) {
        List<Seat> availableSeats = seatRepository.findByIsOccupiedFalse();

        return availableSeats.stream()
                .sorted(Comparator.comparing((Seat seat) -> {
                    int score = 0;
                    if (preferWindow && seat.isWindow()) score += 1;
                    if (preferLegroom && seat.isHasExtraLegroom()) score += 1;
                    if (preferExit && seat.isNearExit()) score += 1;
                    return -score; // soovime kõige sobivamat
                }))
                .findFirst();
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }
}

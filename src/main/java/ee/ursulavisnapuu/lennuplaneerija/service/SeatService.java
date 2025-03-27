package ee.ursulavisnapuu.lennuplaneerija.service;

import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Seat> availableSeats = seatRepository.findByOccupiedFalse();

        return availableSeats.stream()
                .sorted((s1, s2) -> {
                    int s1Score = 0;
                    int s2Score = 0;

                    if (preferWindow && s1.isSeatWindow()) s1Score += 1;
                    if (preferLegroom && s1.isExtraLegroom()) s1Score += 1;
                    if (preferExit && s1.isNearExit()) s1Score += 1;

                    if (preferWindow && s2.isSeatWindow()) s2Score += 1;
                    if (preferLegroom && s2.isExtraLegroom()) s2Score += 1;
                    if (preferExit && s2.isNearExit()) s2Score += 1;

                    return Integer.compare(s2Score, s1Score);
                })
                .findFirst();
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }
}
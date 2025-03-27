package ee.ursulavisnapuu.lennuplaneerija.config;

import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SeatRepository seatRepository;

    public DataLoader(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seatRepository.count() == 0) {
            seatRepository.save(Seat.builder().rowNumber(1).seatLetter('A').seatWindow(true).extraLegroom(false).nearExit(true).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(1).seatLetter('B').seatWindow(false).extraLegroom(true).nearExit(true).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(2).seatLetter('C').seatWindow(false).extraLegroom(false).nearExit(false).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(3).seatLetter('F').seatWindow(true).extraLegroom(true).nearExit(true).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(4).seatLetter('D').seatWindow(false).extraLegroom(true).nearExit(false).occupied(true).build());

            System.out.println("\u2714\uFE0F Istekohad edukalt lisatud!");
        }
    }
}

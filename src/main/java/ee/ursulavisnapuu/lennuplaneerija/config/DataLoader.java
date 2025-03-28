package ee.ursulavisnapuu.lennuplaneerija.config;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.repository.FlightRepository;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;

    public DataLoader(SeatRepository seatRepository, FlightRepository flightRepository) {
        this.seatRepository = seatRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seatRepository.count() == 0) {
            seatRepository.save(Seat.builder().rowNumber(1).seatLetter('A').seatWindow(true).extraLegroom(false).nearExit(true).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(1).seatLetter('B').seatWindow(false).extraLegroom(true).nearExit(true).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(2).seatLetter('C').seatWindow(false).extraLegroom(false).nearExit(false).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(3).seatLetter('F').seatWindow(true).extraLegroom(true).nearExit(true).occupied(false).build());
            seatRepository.save(Seat.builder().rowNumber(4).seatLetter('D').seatWindow(false).extraLegroom(true).nearExit(false).occupied(true).build());

            System.out.println("✔️ Istekohad edukalt lisatud!");
        }

        if (flightRepository.count() == 0) {
            flightRepository.save(Flight.builder().origin("Tallinn").destination("Helsinki").date("2025-04-10").price(79.99).build());
            flightRepository.save(Flight.builder().origin("Tallinn").destination("Stockholm").date("2025-04-12").price(89.99).build());
            flightRepository.save(Flight.builder().origin("Tallinn").destination("Oslo").date("2025-04-15").price(99.99).build());

            System.out.println("✔️ Lennud edukalt lisatud!");
        }
    }
}

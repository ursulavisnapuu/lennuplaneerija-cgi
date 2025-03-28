package ee.ursulavisnapuu.lennuplaneerija.config;

import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.model.Passenger;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import ee.ursulavisnapuu.lennuplaneerija.repository.PassengerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SeatRepository seatRepository;
    private final PassengerRepository passengerRepository;

    public DataLoader(SeatRepository seatRepository, PassengerRepository passengerRepository) {
        this.seatRepository = seatRepository;
        this.passengerRepository = passengerRepository;
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

        if (passengerRepository.count() == 0) {
            passengerRepository.save(Passenger.builder().firstName("Mari").lastName("Maasikas").email("mari@example.com").build());
            passengerRepository.save(Passenger.builder().firstName("Jaan").lastName("Kask").email("jaan@example.com").build());
            passengerRepository.save(Passenger.builder().firstName("Kati").lastName("Kuusk").email("kati@example.com").build());
            System.out.println("✔️ Testreisijad edukalt lisatud!");
        }
    }
}

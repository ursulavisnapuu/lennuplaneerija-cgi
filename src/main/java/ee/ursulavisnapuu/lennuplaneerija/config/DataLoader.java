package ee.ursulavisnapuu.lennuplaneerija.config;

import ee.ursulavisnapuu.lennuplaneerija.model.Flight;
import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.repository.FlightRepository;
import ee.ursulavisnapuu.lennuplaneerija.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(FlightRepository flightRepository, SeatRepository seatRepository) {
        return args -> {
            if (flightRepository.count() == 0) {
                Flight flight1 = Flight.builder()
                        .origin("Tallinn")
                        .destination("Barcelona")
                        .date("2025-06-10")
                        .price(179.99)
                        .build();

                Flight flight2 = Flight.builder()
                        .origin("Tartu")
                        .destination("Helsinki")
                        .date("2025-07-15")
                        .price(89.99)
                        .build();

                flightRepository.save(flight1);
                flightRepository.save(flight2);

                // Lisa istekohad flight1 külge
                seatRepository.save(Seat.builder()
                        .rowNumber(1)
                        .seatLetter("A")
                        .occupied(false)
                        .seatWindow(true)
                        .extraLegroom(true)
                        .nearExit(false)
                        .flight(flight1)
                        .build());

                seatRepository.save(Seat.builder()
                        .rowNumber(1)
                        .seatLetter("B")
                        .occupied(true)
                        .seatWindow(false)
                        .extraLegroom(false)
                        .nearExit(true)
                        .flight(flight1)
                        .build());

                seatRepository.save(Seat.builder()
                        .rowNumber(2)
                        .seatLetter("C")
                        .occupied(false)
                        .seatWindow(false)
                        .extraLegroom(true)
                        .nearExit(true)
                        .flight(flight1)
                        .build());

                // Lisa paar kohta flight2 külge ka
                seatRepository.save(Seat.builder()
                        .rowNumber(1)
                        .seatLetter("A")
                        .occupied(false)
                        .seatWindow(true)
                        .extraLegroom(false)
                        .nearExit(false)
                        .flight(flight2)
                        .build());

                seatRepository.save(Seat.builder()
                        .rowNumber(1)
                        .seatLetter("B")
                        .occupied(false)
                        .seatWindow(false)
                        .extraLegroom(true)
                        .nearExit(false)
                        .flight(flight2)
                        .build());
            }
        };
    }
}

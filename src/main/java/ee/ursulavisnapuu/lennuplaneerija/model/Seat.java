package ee.ursulavisnapuu.lennuplaneerija.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNumber;
    private String seatLetter;
    private boolean occupied;
    private boolean seatWindow;
    private boolean extraLegroom;
    private boolean nearExit;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}

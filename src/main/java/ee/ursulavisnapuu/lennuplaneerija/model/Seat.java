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
    private char seatLetter;

    private boolean occupied;

    @Column(name = "seat_window")
    private boolean seatWindow;

    private boolean extraLegroom;
    private boolean nearExit;
}
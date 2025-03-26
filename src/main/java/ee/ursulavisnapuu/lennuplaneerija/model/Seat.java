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

    private int rowNumber;         // rida (nt 1–30)
    private char seatLetter;       // A–F
    private boolean isOccupied;    // kas on juba võetud
    private boolean isWindow;      // kas on akna all
    private boolean hasExtraLegroom; // kas on rohkem jalaruumi
    private boolean isNearExit;    // kas on lähedal väljapääsule
}

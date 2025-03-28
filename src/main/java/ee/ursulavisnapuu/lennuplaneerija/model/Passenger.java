package ee.ursulavisnapuu.lennuplaneerija.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

   @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
}



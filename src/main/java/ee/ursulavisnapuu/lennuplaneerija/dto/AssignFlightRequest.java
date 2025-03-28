package ee.ursulavisnapuu.lennuplaneerija.dto;

import lombok.Data;
import java.util.List;

@Data
public class AssignFlightRequest {
    private List<Long> passengerIds;
    private Long flightId;
}

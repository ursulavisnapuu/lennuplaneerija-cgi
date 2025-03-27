package ee.ursulavisnapuu.lennuplaneerija.controller;

import ee.ursulavisnapuu.lennuplaneerija.model.Seat;
import ee.ursulavisnapuu.lennuplaneerija.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/suggest")
    public Optional<Seat> suggestSeat(@RequestParam(name = "window", defaultValue = "false") boolean preferWindow,
                                      @RequestParam(name = "legroom", defaultValue = "false") boolean preferLegroom,
                                      @RequestParam(name = "exit", defaultValue = "false") boolean preferExit) {
        return seatService.suggestSeat(preferWindow, preferLegroom, preferExit);
    }

    @PostMapping
    public Seat createSeat(@RequestBody Seat seat) {
        return seatService.saveSeat(seat);
    }
}

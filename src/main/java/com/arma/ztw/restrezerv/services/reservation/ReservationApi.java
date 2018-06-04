package com.arma.ztw.restrezerv.services.reservation;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@Api(value = "reservation")
public class ReservationApi implements RestApi<Reservation> {

    @Autowired
    ReservationRepository repository;

    @GetMapping(value = "")
    public Iterable<Reservation> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Reservation getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Reservation restaurant) {
        repository.save(restaurant);
        if (restaurant == null) {
            return new ResponseEntity("Reservation was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Reservation saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        Reservation dao = repository.findById(id).get();
        dao.setEmpty(reservation.getEmpty());
        dao.setReservationFrom(reservation.getReservationFrom());
        dao.setReservationTo(reservation.getReservationTo());
        dao.setTable(reservation.getTable());
        dao.setRestaurant(reservation.getRestaurant());
        dao.setUsers(reservation.getUsers());
        repository.save(dao);
        return new ResponseEntity("Reservation updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Reservation deleted successfully", HttpStatus.OK);
    }
}

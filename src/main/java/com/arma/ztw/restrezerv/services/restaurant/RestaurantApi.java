package com.arma.ztw.restrezerv.services.restaurant;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@Api(value = "restaurant")
public class RestaurantApi implements RestApi<Restaurant> {

    @Autowired
    RestaurantRepository repository;

    @GetMapping(value = "")
    public Iterable<Restaurant> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Restaurant getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Restaurant restaurant) {
        repository.save(restaurant);
        if (restaurant == null) {
            return new ResponseEntity("Restaurant was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Restaurant saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Restaurant restaurant) {
        Restaurant dao = repository.findById(id).get();
        dao.setName(restaurant.getName());
        dao.setCategories(restaurant.getCategories());
        dao.setDescription(restaurant.getDescription());
        dao.setEmployees(restaurant.getEmployees());
        dao.setLocalization(restaurant.getLocalization());
        dao.setMenu(restaurant.getMenu());
        dao.setTables(restaurant.getTables());
        repository.save(dao);
        return new ResponseEntity("Restaurant updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Restaurant deleted successfully", HttpStatus.OK);
    }
}

package com.arma.ztw.restrezerv.services.localization;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localization")
@Api(value = "localization")
public class LocalizationApi implements RestApi<Localization> {

    @Autowired
    LocalizationRepository repository;

    @GetMapping(value = "")
    public Iterable<Localization> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Localization getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Localization localization) {
        repository.save(localization);
        if (localization == null) {
            return new ResponseEntity("Localization was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Localization saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Localization localization) {
        Localization dao = repository.findById(id).get();
        dao.setCity(localization.getCity());
        dao.setStreet(localization.getStreet());
        dao.setHomeNo(localization.getHomeNo());
        dao.setLatitude(localization.getLatitude());
        dao.setLongitude(localization.getLongitude());
        repository.save(dao);
        return new ResponseEntity("Localization updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Localization deleted successfully", HttpStatus.OK);
    }
}

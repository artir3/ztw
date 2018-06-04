package com.arma.ztw.restrezerv.services.table;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
@Api(value = "table")
public class TablesApi implements RestApi<Tables> {

    @Autowired
    TablesRepository repository;

    @GetMapping(value = "")
    public Iterable<Tables> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Tables getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Tables tables) {
        repository.save(tables);
        if (tables == null) {
            return new ResponseEntity("Tables was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Tables saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Tables tables) {
        Tables dao = repository.findById(id).get();
        dao.setExtendable(tables.getExtendable());
        dao.setNaxNoSites(tables.getNaxNoSites());
        dao.setNoSites(tables.getNoSites());
        dao.setOpen(tables.getOpen());
        dao.setType(tables.getType());
        repository.save(dao);
        return new ResponseEntity("Tables updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Tables deleted successfully", HttpStatus.OK);
    }
}

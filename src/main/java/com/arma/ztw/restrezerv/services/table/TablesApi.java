package com.arma.ztw.restrezerv.services.table;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/table")
@Api(value = "table")
public class TablesApi implements RestApi<TablesDTO> {

    @Autowired
    TablesRepository repository;

    @GetMapping(value = "")
    public Iterable<TablesDTO> list(Model model) {
        List<TablesDTO> res = new ArrayList<>();
        for (Tables tables: repository.findAll()) {
            res.add(copyToDTO(tables));
        }
        return res;
    }

    @GetMapping(value = "{id}")
    public TablesDTO getById(@PathVariable("id") Long id, Model model) {
        return copyToDTO(repository.findById(id).get());
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody TablesDTO tables) {
        repository.save(copyToDAO(tables));
        if (tables == null) {
            return new ResponseEntity("Tables was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Tables saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TablesDTO tables) {
        tables.setId(id);
        repository.save(copyToDAO(tables));
        return new ResponseEntity("Tables updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Tables deleted successfully", HttpStatus.OK);
    }

    private Tables copyToDAO(@RequestBody TablesDTO tables) {
        Tables dao = null;
        if (tables.getId() == null) {
            tables = new TablesDTO();
        } dao = repository.findById(tables.getId()).get();
        dao.setExtendable(tables.getExtendable());
        dao.setNaxNoSites(tables.getNaxNoSites());
        dao.setNoSites(tables.getNoSites());
        dao.setOpen(tables.getOpen());
        dao.setType(tables.getType());
        return dao;
    }

    private TablesDTO copyToDTO(Tables tables) {
        TablesDTO dao = new TablesDTO();
        dao.setId(tables.getId());
        dao.setExtendable(tables.getExtendable());
        dao.setNaxNoSites(tables.getNaxNoSites());
        dao.setNoSites(tables.getNoSites());
        dao.setOpen(tables.getOpen());
        dao.setType(tables.getType());
        return dao;
    }
}

package com.arma.ztw.restrezerv.services.employee;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employee")
@Api(value = "Employee Api")
public class EmployeeApi implements RestApi<Employee> {

    @Autowired
    EmployeeRepository repository;

    @GetMapping(value = "")
    public Iterable<Employee> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Employee getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Employee employee) {
        repository.save(employee);
        if (employee == null) {
            return new ResponseEntity("Employee was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Employee saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee dao = repository.findById(id).get();
        dao.setPermissions(employee.getPermissions());
        repository.save(dao);
        return new ResponseEntity("Employee updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Employee deleted successfully", HttpStatus.OK);
    }
}

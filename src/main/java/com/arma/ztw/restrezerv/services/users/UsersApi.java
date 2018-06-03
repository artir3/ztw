package com.arma.ztw.restrezerv.services.users;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(value = "users")
public class UsersApi implements RestApi<Users> {

    @Autowired
    UsersRepository repository;

    @GetMapping(value = "")
    public Iterable<Users> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Users getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Users users) {
        repository.save(users);
        if (users == null) {
            return new ResponseEntity("User was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Users users) {
        Users user = repository.findById(id).get();
        user.setName(users.getName());
        user.setSurname(users.getSurname());
        user.setCity(users.getCity());
        user.setEmail(users.getEmail());
        user.setPassword(users.getPassword());
        repository.save(user);
        return new ResponseEntity("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("User deleted successfully", HttpStatus.OK);
    }
}

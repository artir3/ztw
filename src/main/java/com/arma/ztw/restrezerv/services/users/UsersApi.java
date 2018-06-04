package com.arma.ztw.restrezerv.services.users;

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
@RequestMapping("/users")
@Api(value = "users")
public class UsersApi implements RestApi<UsersDTO> {

    @Autowired
    UsersRepository repository;

    @GetMapping(value = "")
    public Iterable<UsersDTO> list(Model model) {
        List<UsersDTO> res = new ArrayList<>();
        for (Users users : repository.findAll()) {
            res.add(userToDTO(users));
        }
        return res;
    }

    @GetMapping(value = "{id}")
    public UsersDTO getById(@PathVariable("id") Long id, Model model) {
        return userToDTO(repository.findById(id).get());
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody UsersDTO users) {
        repository.save(usersToDao(users));
        if (users == null) {
            return new ResponseEntity("User was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsersDTO users) {
        users.setId(id);
        repository.save(usersToDao(users));
        return new ResponseEntity("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("User deleted successfully", HttpStatus.OK);
    }

    private Users usersToDao(@RequestBody UsersDTO users) {
        Users user = null;
        if (users.getId() == null) {
            user = new Users();
        } else repository.findById(users.getId()).get();
        user.setName(users.getName());
        user.setSurname(users.getSurname());
        user.setCity(users.getCity());
        user.setEmail(users.getEmail());
//        user.setPassword(users.getPassword());
        return user;
    }

    private UsersDTO userToDTO(Users users) {
        UsersDTO user = new UsersDTO();
        user.setId(users.getId());
        user.setName(users.getName());
        user.setSurname(users.getSurname());
        user.setCity(users.getCity());
        user.setEmail(users.getEmail());
//        user.setPassword(users.getPassword());
        return user;
    }
}

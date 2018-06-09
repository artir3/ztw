package com.arma.ztw.restrezerv.services.users;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "users")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping(value = "/ul/")
    public UsersDTO login(@RequestBody UserLoginDTO users) {
        Users user = repository.findByEmail(users.getEmail());
        if (user == null || !user.getPassword().toLowerCase().equals(users.getPassword().toLowerCase())) {
            return new UsersDTO();
        }
        UsersDTO resume = userToDTO(user);
        resume.setAuthorisationToken("topSecret");
        return resume;
    }

    @PostMapping(value = "/pu/")
    public ResponseEntity updataPassword(@RequestBody UserPasswordDTO pass) {
        Users user = repository.findById(pass.getId()).get();
        if (user == null || !user.getPassword().toLowerCase().equals(pass.getPassword().toLowerCase())
                || !user.getPassword().toLowerCase().equals(pass.getOldPassword().toLowerCase())) {
            return new ResponseEntity("Values to update password are incorect", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(pass.getPassword());
        repository.save(user);
        return new ResponseEntity("Password updated successfully", HttpStatus.OK);
    }

    private Users usersToDao(@RequestBody UsersDTO dto) {
        Users user = null;
        if (dto.getId() == null || 0 == dto.getId()) {
            user = new Users();
            user.setCreateDate(LocalDateTime.now());
        } else user = repository.findById(dto.getId()).get();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setCity(dto.getCity());
        user.setEmail(dto.getEmail());
        user.setPassword(user.getPassword());
        user.setModifyDate(LocalDateTime.now());
        return user;
    }

    private UsersDTO userToDTO(Users users) {
        UsersDTO user = new UsersDTO();
        user.setId(users.getId());
        user.setName(users.getName());
        user.setSurname(users.getSurname());
        user.setCity(users.getCity());
        user.setEmail(users.getEmail());
        user.setPassword(users.getPassword());
        return user;
    }

}

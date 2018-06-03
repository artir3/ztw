package com.arma.ztw.restrezerv.controllers;

import com.arma.ztw.restrezerv.models.Users;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping(value = "")
    public List<Users> list(){
        return Arrays.asList(new Users("Tom", "Nick", "email","ACME","asd"));
    }

    @GetMapping(value = "{id}")
    public Users getById(@PathVariable("id") Integer id){
        return new Users("Jery", "Nick", "email","ACME","asd");
    }

    @PostMapping(value = "")
    public int save(@RequestBody Users users){
        return 404;
    }

    @PutMapping(value = "{id}")
    public int update(@PathVariable("id") Integer id, @RequestBody Users users){
        return 500;
    }

    @DeleteMapping(value = "{id}")
    public int delete(@PathVariable("id") String id){
        return 21;
    }
}

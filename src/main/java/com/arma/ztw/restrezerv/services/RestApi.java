package com.arma.ztw.restrezerv.services;

import io.swagger.models.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface RestApi<T> {

    @GetMapping(value = "")
    public Iterable<T> list(Model model);

    @GetMapping(value = "{id}")
    public T getById(@PathVariable("id") Long id, Model model);

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody T t);

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody T t);

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id);

}

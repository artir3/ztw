package com.arma.ztw.restrezerv.services.category;

import com.arma.ztw.restrezerv.services.RestApi;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Api(value = "category")
public class CategoryApi implements RestApi<Category> {

    @Autowired
    CategoryRepository repository;

    @GetMapping(value = "")
    public Iterable<Category> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Category getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Category category) {
        repository.save(category);
        if (category == null) {
            return new ResponseEntity("Category was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Category saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Category category) {
        Category user = repository.findById(id).get();
        user.setName(category.getName());
        user.setDescription(category.getDescription());
        repository.save(user);
        return new ResponseEntity("Category updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Category deleted successfully", HttpStatus.OK);
    }
}

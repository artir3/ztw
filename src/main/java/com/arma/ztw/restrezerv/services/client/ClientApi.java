package com.arma.ztw.restrezerv.services.client;

import com.arma.ztw.restrezerv.services.RestApi;
import com.arma.ztw.restrezerv.services.liked_restaurants.LikedRestaurants;
import com.arma.ztw.restrezerv.services.liked_restaurants.LikedRestaurantsRepository;
import io.swagger.annotations.Api;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/client")
@Api(value = "Client Api")
public class ClientApi implements RestApi<Client> {

    @Autowired
    ClientRepository repository;

    @Autowired
    LikedRestaurantsRepository likedRestaurantsRepository;

    @GetMapping(value = "")
    public Iterable<Client> list(Model model) {
        return repository.findAll();
    }

    @GetMapping(value = "{id}")
    public Client getById(@PathVariable("id") Long id, Model model) {
        return repository.findById(id).get();
    }

    @PostMapping(value = "")
    public ResponseEntity save(@RequestBody Client client) {
        repository.save(client);
        if (client == null) {
            return new ResponseEntity("Client was not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Client saved successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Client client) {
        Client dao = repository.findById(id).get();
        dao.setDescription(client.getDescription());
        dao.setStatus(client.getStatus());
        dao.setLikedCategories(client.getLikedCategories());
        dao.setLikedLocalizations(client.getLikedLocalizations());
        Set<LikedRestaurants> likedRestaurantsRepositories = likedRestaurantsRepository.saveOrUpdate(client.getLikedRestaurants());
        dao.setLikedRestaurants(likedRestaurantsRepositories);
        repository.save(dao);
        return new ResponseEntity("Client updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity("Client deleted successfully", HttpStatus.OK);
    }
}

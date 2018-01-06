package ru.natashapetrenko.voting.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.service.DishService;
import ru.natashapetrenko.voting.to.DishTo;
import ru.natashapetrenko.voting.util.DishesUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.assureIdConsistent;
import static ru.natashapetrenko.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantDishRestController{
    static final String REST_URL = "/rest/restaurants/{restaurant_id}/dishes";

    @Autowired
    private DishService service;

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody Dish dish, @PathVariable("restaurant_id") int restaurantId, @PathVariable("id") int id) {
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }

    @GetMapping
    public List<DishTo> getByDate(@PathVariable("restaurant_id") int restaurantId,
                                  @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return DishesUtil.getAll(service.getByDate(date, restaurantId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restaurant_id") int restaurantId) {
        checkNew(dish);
        Dish created = service.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
package ru.natashapetrenko.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.service.DishService;
import ru.natashapetrenko.voting.service.RestaurantService;
import ru.natashapetrenko.voting.to.RestaurantTo;
import ru.natashapetrenko.voting.util.RestaurantsUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.assureIdConsistent;
import static ru.natashapetrenko.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController{
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }

    @GetMapping
    public List<RestaurantTo> getAll(@RequestParam(value = "date", required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Restaurant> restaurants;
        if (date == null) {
            restaurants = restaurantService.getAll();
        } else {
            restaurants = dishService.getRestaurantsByDate(date);
        }
        return RestaurantsUtil.getAll(restaurants);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
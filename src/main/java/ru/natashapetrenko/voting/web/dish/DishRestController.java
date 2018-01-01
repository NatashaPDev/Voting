package ru.natashapetrenko.voting.web.dish;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.to.DishWithExceed;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends AbstractDishController {
    static final String REST_URL = "/rest/menus";

    @Override
    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<DishWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        super.update(dish, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        Dish created = super.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<DishWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return super.getBetween(startDate);
    }
}
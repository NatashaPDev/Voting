package ru.natashapetrenko.voting.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.natashapetrenko.voting.service.DishService;
import ru.natashapetrenko.voting.to.DishTo;
import ru.natashapetrenko.voting.util.DishesUtil;

import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController{
    static final String REST_URL = "/rest/dishes";

    @Autowired
    private DishService service;

    @GetMapping
    public List<DishTo> getAll() {
        return DishesUtil.getAll(service.getAll());
    }

}
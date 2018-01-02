package ru.natashapetrenko.voting.service;


import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.model.User;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant user);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant user);

    List<Restaurant> getAll();

}
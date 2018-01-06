package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DishService {
    Dish get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Dish> getAll();

    List<Dish> getByDate(LocalDate date, int restaurantId);

    Dish update(Dish dish, int restaurantId) throws NotFoundException;

    Dish create(Dish dish, int restaurantId);

    List<Restaurant> getRestaurantsByDate(LocalDate date);
}
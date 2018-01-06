package ru.natashapetrenko.voting.repository;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getByDate(LocalDate date, int restaurantId);

    List<Restaurant> getRestaurantsByDate(LocalDate date);
}

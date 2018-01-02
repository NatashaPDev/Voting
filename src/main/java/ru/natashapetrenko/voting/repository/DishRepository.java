package ru.natashapetrenko.voting.repository;

import ru.natashapetrenko.voting.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish);

    // false if dish do not belong to userId
    boolean delete(int id);

    // null if dish do not belong to userId
    Dish get(int id);

    // ORDERED dateTime desc
    List<Dish> getAll();

    // ORDERED dateTime desc
    List<Dish> getBetween(LocalDateTime startDate);

    List<Dish> getByRestaurantId(int restaurantId);
}

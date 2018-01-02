package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface DishService {
    Dish get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    default List<Dish> getBetweenDates(LocalDate startDate) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN));
    }

    List<Dish> getBetweenDateTimes(LocalDateTime startDateTime);

    List<Dish> getAll();

    List<Dish> getByRestaurantId(int restaurantId);

    Dish update(Dish dish) throws NotFoundException;

    Dish create(Dish dish);
}
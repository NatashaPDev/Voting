package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface DishService {
    Dish get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    default List<Dish> getBetweenDates(LocalDate startDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), userId);
    }

    List<Dish> getBetweenDateTimes(LocalDateTime startDateTime, int userId);

    List<Dish> getAll(int userId);

    Dish update(Dish dish, int userId) throws NotFoundException;

    Dish create(Dish dish, int userId);
}
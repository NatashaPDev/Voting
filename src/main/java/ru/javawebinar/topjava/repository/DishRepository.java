package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository {
    // null if updated dish do not belong to userId
    Dish save(Dish dish, int userId);

    // false if dish do not belong to userId
    boolean delete(int id, int userId);

    // null if dish do not belong to userId
    Dish get(int id, int userId);

    // ORDERED dateTime desc
    List<Dish> getAll(int userId);

    // ORDERED dateTime desc
    List<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}

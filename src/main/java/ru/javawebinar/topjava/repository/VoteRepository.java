package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if updated dish do not belong to userId
    Vote save(Vote vote, int userId);

    // false if dish do not belong to userId
    boolean delete(int id, int userId);

    // null if dish do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll(int userId);

//    // ORDERED dateTime desc
//    List<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}

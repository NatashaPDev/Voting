package ru.natashapetrenko.voting.repository;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if updated dish do not belong to userId
    Vote save(Vote vote, int userId);

    // null if dish do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll();

    List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}

package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface VoteService {
    Vote get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Vote> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Vote> getAll(int userId);

    Vote update(Vote dish, int userId) throws NotFoundException;

    Vote create(Vote dish, int userId);
}
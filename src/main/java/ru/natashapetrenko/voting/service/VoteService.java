package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface VoteService {

    List<Vote> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Vote> getAll();

    Vote create(Vote dish, int userId);
}
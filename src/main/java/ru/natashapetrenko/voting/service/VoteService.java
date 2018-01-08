package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    List<Vote> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Vote> getAll();

    Vote create(Vote dish, int userId);
}
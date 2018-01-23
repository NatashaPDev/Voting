package ru.natashapetrenko.voting.service;

import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    List<Vote> getByDate(LocalDate date, int userId);

    List<Vote> getAll();

    Vote create(Vote dish, int userId);
}
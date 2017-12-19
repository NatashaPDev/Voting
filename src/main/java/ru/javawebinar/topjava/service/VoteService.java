package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface VoteService {
    Vote get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

//    default List<Vote> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
//        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
//    }

    //List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Vote> getAll(int userId);

    Vote update(Vote meal, int userId) throws NotFoundException;

    Vote create(Vote meal, int userId);
}
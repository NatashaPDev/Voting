package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.DishRepository;
import ru.javawebinar.topjava.repository.VoteRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

//    @Override
//    public List<Dish> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
//        Assert.notNull(startDateTime, "startDateTime must not be null");
//        Assert.notNull(endDateTime, "endDateTime  must not be null");
//        return repository.getBetween(startDateTime, endDateTime, userId);
//    }

    @Override
    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Vote update(Vote vote, int userId) {
        return checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    @Override
    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId);
    }
}
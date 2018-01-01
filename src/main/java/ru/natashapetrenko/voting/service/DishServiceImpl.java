package ru.natashapetrenko.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Dish> getBetweenDateTimes(LocalDateTime startDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        return repository.getBetween(startDateTime, userId);
    }

    @Override
    public List<Dish> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Dish update(Dish dish, int userId) {
        return checkNotFoundWithId(repository.save(dish, userId), dish.getId());
    }

    @Override
    public Dish create(Dish dish, int userId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, userId);
    }
}
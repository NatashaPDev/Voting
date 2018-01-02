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
    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Dish> getBetweenDateTimes(LocalDateTime startDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        return repository.getBetween(startDateTime);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Dish> getByRestaurantId(int restaurantId) {
        Assert.notNull(restaurantId, "restaurantId must not be null");
        return repository.getByRestaurantId(restaurantId);
    }

    @Override
    public Dish update(Dish dish) {
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @Override
    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }
}
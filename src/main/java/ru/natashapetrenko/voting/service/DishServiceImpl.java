package ru.natashapetrenko.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.repository.DishRepository;

import java.time.LocalDate;
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
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Dish> getByDate(LocalDate date, int restaurantId) {
        Assert.notNull(date, "date must not be null");
        return repository.getByDate(date, restaurantId);
    }

    @Override
    public Dish update(Dish dish, int restaurantId) {
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }

    @Override
    public List<Restaurant> getRestaurantsByDate(LocalDate date) {
        return repository.getRestaurantsByDate(date);
    }
}
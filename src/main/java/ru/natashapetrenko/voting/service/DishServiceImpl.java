package ru.natashapetrenko.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.repository.datajpa.CrudDishRepository;
import ru.natashapetrenko.voting.repository.datajpa.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Dish get(int id) {
        return checkNotFoundWithId(crudDishRepository.findById(id).orElse(null), id);
    }

    @CacheEvict(value = {"restaurants_by_date", "dishes_by_date"}, allEntries = true)
    @Override
    public void delete(int id) {
        checkNotFoundWithId(crudDishRepository.delete(id) != 0, id);
    }

    @Override
    public List<Dish> getAll() {
        return crudDishRepository.getAll();
    }

    @Cacheable("dishes_by_date")
    @Override
    public List<Dish> getByDate(LocalDate date, int restaurantId) {
        Assert.notNull(date, "date must not be null");
        return crudDishRepository.getByDate(date, restaurantId);
    }

    @CacheEvict(value = {"restaurants_by_date", "dishes_by_date"}, allEntries = true)
    @Override
    public Dish update(Dish dish, int restaurantId) {
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return checkNotFoundWithId(crudDishRepository.save(dish), dish.getId());
    }

    @CacheEvict(value = {"restaurants_by_date", "dishes_by_date"}, allEntries = true)
    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Cacheable("restaurants_by_date")
    @Override
    public List<Restaurant> getRestaurantsByDate(LocalDate date) {
        return crudDishRepository.getRestaurantsByDate(date);
    }
}
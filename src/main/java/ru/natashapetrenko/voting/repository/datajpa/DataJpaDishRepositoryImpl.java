package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.repository.DishRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return crudDishRepository.getAll();
    }

    @Override
    public List<Dish> getByDate(LocalDate date, int restaurantId) {
        return crudDishRepository.getByDate(date, restaurantId);
    }

    @Override
    public List<Restaurant> getRestaurantsByDate(LocalDate date) {
        return crudDishRepository.getRestaurantsByDate(date);
    }

}

package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Override
    @Transactional
    public Dish save(Dish dish) {
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
    public List<Dish> getBetween(LocalDateTime startDate) {
        return crudDishRepository.getBetween(startDate);
    }

    @Override
    public List<Dish> getByRestaurantId(int restaurantId) {
        return crudDishRepository.getByRestaurantId(restaurantId);
    }
}

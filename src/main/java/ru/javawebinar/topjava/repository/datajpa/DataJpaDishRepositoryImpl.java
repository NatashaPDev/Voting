package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int userId) {
        if (!dish.isNew() && get(dish.getId(), userId) == null) {
            return null;
        }
        dish.setUser(crudUserRepository.getOne(userId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudDishRepository.delete(id, userId) != 0;
    }

    @Override
    public Dish get(int id, int userId) {
        return crudDishRepository.findById(id).filter(dish -> dish.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Dish> getAll(int userId) {
        return crudDishRepository.getAll(userId);
    }

    @Override
    public List<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudDishRepository.getBetween(startDate, endDate, userId);
    }
}

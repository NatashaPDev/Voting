package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.model.User;
import ru.natashapetrenko.voting.repository.RestaurantRepository;
import ru.natashapetrenko.voting.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Override
    public Restaurant save(Restaurant user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }
}

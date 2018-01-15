package ru.natashapetrenko.voting.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.repository.JpaUtil;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static ru.natashapetrenko.voting.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Restaurant(null, "  ")), ConstraintViolationException.class);
    }

    @Before
    public void setUp() {
        cacheManager.getCache("restaurants").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void create() {
        Restaurant newRestaurant = new Restaurant(null, "New");
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), RESTAURANT1, newRestaurant, RESTAURANT2);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT1, RESTAURANT2);
    }
}
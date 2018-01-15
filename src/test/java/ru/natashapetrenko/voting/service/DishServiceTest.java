package ru.natashapetrenko.voting.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.natashapetrenko.voting.DishTestData;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;
import static ru.natashapetrenko.voting.DishTestData.*;
import static ru.natashapetrenko.voting.RestaurantTestData.RESTAURANT1;
import static ru.natashapetrenko.voting.RestaurantTestData.RESTAURANT1_ID;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;

    @Test
    public void delete() {
        service.delete(DISH1_ID);
        assertMatch(service.getAll(), DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }

    @Test
    public void create() {
        Dish created = getCreated();
        service.create(created, RESTAURANT1_ID);
        assertMatch(service.getAll(), DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13, created);
    }

    @Test
    public void get() {
        Dish actual = service.get(DISH1_ID);
        assertMatch(actual, DISH1);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

    @Test
    public void update() {
        Dish updated = DishTestData.getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(DISH1_ID), updated);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), DISHES);
    }

    @Test
    public void getByDate() {
        assertMatch(service.getByDate(
                LocalDate.of(2018, Month.JANUARY, 1), RESTAURANT1_ID), DISH1, DISH2, DISH3, DISH4);
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Dish(null, of(2018, Month.JANUARY, 1), "  ", 300, RESTAURANT1), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Dish(null, null, "Description", 300, RESTAURANT1), RESTAURANT1_ID), ConstraintViolationException.class);
    }
}
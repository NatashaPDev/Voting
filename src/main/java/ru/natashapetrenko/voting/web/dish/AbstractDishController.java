package ru.natashapetrenko.voting.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.natashapetrenko.voting.AuthorizedUser;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.service.DishService;
import ru.natashapetrenko.voting.to.DishWithExceed;
import ru.natashapetrenko.voting.util.DateTimeUtil;
import ru.natashapetrenko.voting.util.DishesUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.assureIdConsistent;
import static ru.natashapetrenko.voting.util.ValidationUtil.checkNew;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish get(int id) {
        return service.get(id);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public List<DishWithExceed> getAll() {
        return DishesUtil.getWithExceeded(service.getAll());
    }

    public Dish create(Dish dish) {
        checkNew(dish);
        return service.create(dish);
    }

    public void update(Dish dish, int id) {
        assureIdConsistent(dish, id);
        service.update(dish);
    }

    public List<DishWithExceed> getBetween(LocalDate startDate) {

        List<Dish> dishesDateFiltered = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE);

        return DishesUtil.getFilteredWithExceeded(dishesDateFiltered,
                LocalTime.MIN
        );
    }
}
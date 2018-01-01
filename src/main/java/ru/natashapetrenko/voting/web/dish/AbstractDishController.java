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
        int userId = AuthorizedUser.id();
        log.info("get dish {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete dish {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<DishWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return DishesUtil.getWithExceeded(service.getAll(userId));
    }

    public Dish create(Dish dish) {
        int userId = AuthorizedUser.id();
        checkNew(dish);
        log.info("create {} for user {}", dish, userId);
        return service.create(dish, userId);
    }

    public void update(Dish dish, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(dish, id);
        log.info("update {} for user {}", dish, userId);
        service.update(dish, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<DishWithExceed> getBetween(LocalDate startDate) {
        int userId = AuthorizedUser.id();

        List<Dish> dishesDateFiltered = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE, userId);

        return DishesUtil.getFilteredWithExceeded(dishesDateFiltered,
                LocalTime.MIN
        );
    }
}
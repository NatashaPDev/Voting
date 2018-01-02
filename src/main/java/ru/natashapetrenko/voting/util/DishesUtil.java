package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.to.DishWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DishesUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    private DishesUtil() {
    }

    public static List<DishWithExceed> getWithExceeded(Collection<Dish> dishes) {
        return dishes.stream()
                .map(dish -> createWithExceed(dish))
                .collect(toList());
    }

    public static List<DishWithExceed> getFilteredWithExceeded(Collection<Dish> dishes, LocalTime startTime) {

        return dishes.stream()
                .filter(dish -> DateTimeUtil.isBetween(dish.getTime(), startTime))
                .map(dish -> createWithExceed(dish))
                .collect(toList());
    }

    private static DishWithExceed createWithExceed(Dish dish) {
        return new DishWithExceed(dish.getId(), dish.getDateTime(), dish.getDescription(), dish.getPrice(), dish.getRestaurant().getId());
    }

}
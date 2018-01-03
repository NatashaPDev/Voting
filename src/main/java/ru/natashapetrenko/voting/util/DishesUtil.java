package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.to.DishTo;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DishesUtil {

    private DishesUtil() {
    }

    public static List<DishTo> getAll(Collection<Dish> dishes) {
        return dishes.stream()
                .map(dish -> asTo(dish))
                .collect(toList());
    }

    private static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getDate(), dish.getDescription(), dish.getPrice(), dish.getRestaurant().getId());
    }

}
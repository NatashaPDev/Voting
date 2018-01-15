package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.to.DishTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DishesUtil {

    private DishesUtil() {
    }

    public static List<DishTo> getAll(Collection<Dish> dishes) {
        return dishes.stream()
                .map(DishesUtil::asTo)
                .collect(toList());
    }

    public static DishTo asTo(Dish dish) {
        int restaurantId;
        Restaurant restaurant = dish.getRestaurant();
        if (restaurant != null) {
            restaurantId = restaurant.getId();
        } else {
            restaurantId = 0;
        }
        return new DishTo(dish.getId(), dish.getDate(), dish.getDescription(), dish.getPrice(), restaurantId);
    }

}
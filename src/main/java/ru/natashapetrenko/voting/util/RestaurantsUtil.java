package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.model.Role;
import ru.natashapetrenko.voting.model.User;
import ru.natashapetrenko.voting.to.DishWithExceed;
import ru.natashapetrenko.voting.to.RestaurantTo;
import ru.natashapetrenko.voting.to.UserTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RestaurantsUtil {

    public static Restaurant createNewFromTo(RestaurantTo newUser) {
        return new Restaurant(null, newUser.getName());
    }

    public static RestaurantTo asTo(Restaurant user) {
        return new RestaurantTo(user.getId(), user.getName());
    }

    public static Restaurant updateFromTo(Restaurant user, RestaurantTo userTo) {
        user.setName(userTo.getName());
        return user;
    }

    public static List<RestaurantTo> getAll(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> asTo(restaurant))
                .collect(toList());
    }
}

package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.to.RestaurantTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RestaurantsUtil {

    private static RestaurantTo asTo(Restaurant user) {
        return new RestaurantTo(user.getId(), user.getName());
    }

    public static List<RestaurantTo> getAll(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantsUtil::asTo)
                .collect(toList());
    }
}

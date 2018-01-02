package ru.natashapetrenko.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.service.DishService;
import ru.natashapetrenko.voting.service.RestaurantService;
import ru.natashapetrenko.voting.to.RestaurantTo;
import ru.natashapetrenko.voting.util.RestaurantsUtil;

import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.assureIdConsistent;
import static ru.natashapetrenko.voting.util.ValidationUtil.checkNew;

@Controller
public class AbstractRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    public Restaurant get(int id) {
        return restaurantService.get(id);
    }

    public void delete(int id) {
        restaurantService.delete(id);
    }

    public List<RestaurantTo> getAll() {
        return RestaurantsUtil.getAll(restaurantService.getAll());
    }

    public Restaurant create(Restaurant vote) {
        checkNew(vote);
        return restaurantService.create(vote);
    }

    public void update(Restaurant vote, int id) {
        assureIdConsistent(vote, id);
        restaurantService.update(vote);
    }

    public List<Dish> getDishes(int id) {
        return dishService.getByRestaurantId(id);
    }

}
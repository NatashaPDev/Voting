package ru.natashapetrenko.voting;

import ru.natashapetrenko.voting.model.Dish;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.natashapetrenko.voting.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 4;

    public static final Dish DISH1 = new Dish(DISH1_ID, of(2018, Month.JANUARY, 1), "Chicken", 500);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, of(2018, Month.JANUARY, 1), "Salad", 1000);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, of(2018, Month.JANUARY, 1), "Fried potatoes", 500);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, of(2018, Month.JANUARY, 1), "Juice", 300);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, of(2018, Month.JANUARY, 1), "Noodles", 510);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, of(2018, Month.JANUARY, 1), "Salad", 1000);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, of(2018, Month.JANUARY, 1), "Tea", 200);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, of(2018, Month.JANUARY, 2), "Burger", 500);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, of(2018, Month.JANUARY, 2), "Salad", 1000);
    public static final Dish DISH10 = new Dish(DISH1_ID + 9, of(2018, Month.JANUARY, 2), "Juice", 300);
    public static final Dish DISH11 = new Dish(DISH1_ID + 10, of(2018, Month.JANUARY, 2), "Sushi", 510);
    public static final Dish DISH12 = new Dish(DISH1_ID + 11, of(2018, Month.JANUARY, 2), "Salad", 1000);
    public static final Dish DISH13 = new Dish(DISH1_ID + 12, of(2018, Month.JANUARY, 2), "Tea", 200);

    public static final List<Dish> DISHES = Arrays.asList(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13);

    public static Dish getCreated() {
        return new Dish(null, of(2018, Month.JANUARY, 3), "Created dish", 300);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, DISH1.getDate(), "Updated dish", 200);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}

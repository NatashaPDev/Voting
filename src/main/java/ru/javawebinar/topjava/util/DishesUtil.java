package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.to.DishWithExceed;

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
        return getFilteredWithExceeded(dishes, LocalTime.MIN, LocalTime.MAX);
    }

    public static List<DishWithExceed> getFilteredWithExceeded(Collection<Dish> dishes, LocalTime startTime, LocalTime endTime) {
        Map<LocalDate, Integer> priceSumByDate = dishes.stream()
                .collect(
                        Collectors.groupingBy(Dish::getDate, Collectors.summingInt(Dish::getPrice))
//                      Collectors.toMap(Dish::getDate, Dish::getPrice, Integer::sum)
                );

        return dishes.stream()
                .filter(dish -> DateTimeUtil.isBetween(dish.getTime(), startTime, endTime))
                .map(dish -> createWithExceed(dish))
                .collect(toList());
    }

    private static DishWithExceed createWithExceed(Dish dish) {
        return new DishWithExceed(dish.getId(), dish.getDateTime(), dish.getDescription(), dish.getPrice(), dish.getRestaurant());
    }

/*
    public static List<DishWithExceed> getFilteredWithExceededByCycle(List<Dish> dishes, LocalTime startTime, LocalTime endTime, int pricePerDay) {

        final Map<LocalDate, Integer> priceSumByDate = new HashMap<>();
        dishes.forEach(dish -> priceSumByDate.merge(dish.getDate(), dish.getPrice(), Integer::sum));

        final List<DishWithExceed> dishesWithExceeded = new ArrayList<>();
        dishes.forEach(dish -> {
            if (DateTimeUtil.isBetween(dish.getTime(), startTime, endTime)) {
                dishesWithExceeded.add(createWithExceed(dish, priceSumByDate.get(dish.getDate()) > pricePerDay));
            }
        });
        return dishesWithExceeded;
    }

    public static List<DishWithExceed> getFilteredWithExceededInOnePass(List<Dish> dishes, LocalTime startTime, LocalTime endTime, int pricePerDay) {
        Collection<List<Dish>> list = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getDate)).values();

        return list.stream().flatMap(dayDishes -> {
            boolean exceed = dayDishes.stream().mapToInt(Dish::getPrice).sum() > pricePerDay;
            return dayDishes.stream().filter(dish ->
                    DateTimeUtil.isBetween(dish.getTime(), startTime, endTime))
                    .map(dish -> createWithExceed(dish, exceed));
        }).collect(toList());
    }

    public static List<DishWithExceed> getFilteredWithExceededInOnePass2(List<Dish> dishes, LocalTime startTime, LocalTime endTime, int pricePerDay) {
        final class Aggregate {
            private final List<Dish> dailyDishes = new ArrayList<>();
            private int dailySumOfPrice;

            private void accumulate(Dish dish) {
                dailySumOfPrice += dish.getPrice();
                if (DateTimeUtil.isBetween(dish.getDateTime().toLocalTime(), startTime, endTime)) {
                    dailyDishes.add(dish);
                }
            }

            // never invoked if the upstream is sequential
            private Aggregate combine(Aggregate that) {
                this.dailySumOfPrice += that.dailySumOfPrice;
                this.dailyDishes.addAll(that.dailyDishes);
                return this;
            }

            private Stream<DishWithExceed> finisher() {
                final boolean exceed = dailySumOfPrice > pricePerDay;
                return dailyDishes.stream().map(dish -> createWithExceed(dish, exceed));
            }
        }

        Collection<Stream<DishWithExceed>> values = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getDate,
                        Collector.of(Aggregate::new, Aggregate::accumulate, Aggregate::combine, Aggregate::finisher))
                ).values();

        return values.stream().flatMap(identity()).collect(toList());
    }
*/
}
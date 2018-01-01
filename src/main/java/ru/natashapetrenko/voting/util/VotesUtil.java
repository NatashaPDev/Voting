package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.to.DishWithExceed;
import ru.natashapetrenko.voting.to.VoteTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class VotesUtil {

    //public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    private VotesUtil() {
    }

//    public static List<DishWithExceed> getWithExceeded(Collection<Dish> dishes, int pricePerDay) {
//        return getFilteredWithExceeded(dishes, LocalTime.MIN, LocalTime.MAX, pricePerDay);
//    }

    public static List<VoteTO> getAll(Collection<Vote> votes) {
//        Map<LocalDate, Integer> priceSumByDate = votes.stream()
//                .collect(
//                        Collectors.groupingBy(Dish::getDate, Collectors.summingInt(Dish::getPrice))
////                      Collectors.toMap(Dish::getDate, Dish::getPrice, Integer::sum)
//                );

        return votes.stream()
                .map(vote -> createVoteTO(vote))
                .collect(toList());
    }

    private static VoteTO createVoteTO(Vote vote) {
        return new VoteTO(vote.getId(), vote.getDateTime(), vote.getRestaurant());
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
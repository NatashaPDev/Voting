package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    Dish save(Dish item);

    @Query("SELECT m FROM Dish m ORDER BY m.date")
    List<Dish> getAll();

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m FROM Dish m WHERE m.date=:date AND m.restaurant.id = :restaurant_id")
    List<Dish> getByDate(@Param("date") LocalDate date, @Param("restaurant_id") int restaurantId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT distinct m.restaurant FROM Dish m WHERE m.date=:date")
    List<Restaurant> getRestaurantsByDate(@Param("date") LocalDate date);

}
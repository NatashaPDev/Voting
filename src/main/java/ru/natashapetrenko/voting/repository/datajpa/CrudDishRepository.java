package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    Dish save(Dish item);

    @Query("SELECT m FROM Dish m ORDER BY m.dateTime")
    List<Dish> getAll();

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Dish m WHERE m.dateTime=:startDate ORDER BY m.dateTime DESC")
    List<Dish> getBetween(@Param("startDate") LocalDateTime startDate);

    List<Dish> getByRestaurantId(int restaurantId);
}
package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    Vote save(Vote item);

    @Query("SELECT m FROM Vote m ORDER BY m.dateTime DESC")
    List<Vote> getAll();

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Vote m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Vote> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
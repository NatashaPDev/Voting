package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote item);

    @Query("SELECT m FROM Vote m ORDER BY m.date DESC, m.time DESC")
    List<Vote> getAll();

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Vote m WHERE m.user.id=:userId AND m.date=:date")
    List<Vote> getByDate(@Param("date") LocalDate date, @Param("userId") int userId);
}
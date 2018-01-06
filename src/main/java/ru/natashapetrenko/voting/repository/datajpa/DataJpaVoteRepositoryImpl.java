package ru.natashapetrenko.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.repository.DishRepository;
import ru.natashapetrenko.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.getAll();
    }

    @Override
    public List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudVoteRepository.getBetween(startDate, endDate, userId);
    }
}

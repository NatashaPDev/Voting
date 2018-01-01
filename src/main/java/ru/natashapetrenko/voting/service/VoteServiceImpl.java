package ru.natashapetrenko.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Vote update(Vote vote, int userId) {
        return checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    @Override
    public List<Vote> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Vote create(Vote vote, int userId) {

        Assert.notNull(vote, "vote must not be null");

        LocalDate currentDate = vote.getDate();
        List<Vote> currentVotes = getBetweenDateTimes(LocalDateTime.of(currentDate, LocalTime.MIN),
                LocalDateTime.of(currentDate, LocalTime.MAX),
                userId);

        if (!currentVotes.isEmpty()) {
            if (vote.getTime().compareTo(LocalTime.of(11, 0)) > 0) {
                return null;
            } else {
                Vote currentVote = currentVotes.get(0);
                repository.delete(currentVote.getId(), currentVote.getUser().getId());
            }
        }
        return repository.save(vote, userId);
    }
}
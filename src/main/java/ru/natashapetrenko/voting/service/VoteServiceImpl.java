package ru.natashapetrenko.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.repository.datajpa.CrudUserRepository;
import ru.natashapetrenko.voting.repository.datajpa.CrudVoteRepository;
import ru.natashapetrenko.voting.util.exception.VoteCantBeChangedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private static final int LIMIT_HOUR = 11;

    @Autowired
    private CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    @Override
    public List<Vote> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        return voteRepository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Vote create(Vote vote, int userId) {

        Assert.notNull(vote, "vote must not be null");

        LocalDate currentDate = vote.getDate();
        List<Vote> currentVotes = getBetweenDateTimes(LocalDateTime.of(currentDate, LocalTime.MIN),
                LocalDateTime.of(currentDate, LocalTime.MAX),
                userId);

        if (!currentVotes.isEmpty()) {
            if (vote.getTime().compareTo(LocalTime.of(LIMIT_HOUR, 0)) > 0) {
                throw new VoteCantBeChangedException();
            } else {
                Vote currentVote = currentVotes.get(0);
                currentVote.setDateTime(vote.getDateTime());
                currentVote.setRestaurant(vote.getRestaurant());
                return voteRepository.save(vote);
            }
        }
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

}
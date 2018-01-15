package ru.natashapetrenko.voting.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.natashapetrenko.voting.UserTestData.USER_ID;
import static ru.natashapetrenko.voting.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;


    @Test
    public void create() {
        Vote created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(), created, VOTE3, VOTE4, VOTE1, VOTE2);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), VOTES);
    }

    @Test
    public void getBetweenDateTimes() {
        assertMatch(service.getBetweenDateTimes(LocalDateTime.of(2018, Month.JANUARY, 1, 0, 0, 0),
                LocalDateTime.of(2018, Month.JANUARY, 1, 23, 59, 59), USER_ID), VOTE1);
    }
}
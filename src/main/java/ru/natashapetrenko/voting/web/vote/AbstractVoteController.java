package ru.natashapetrenko.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.natashapetrenko.voting.AuthorizedUser;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.service.VoteService;
import ru.natashapetrenko.voting.to.VoteTo;
import ru.natashapetrenko.voting.util.VotesUtil;

import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.assureIdConsistent;
import static ru.natashapetrenko.voting.util.ValidationUtil.checkNew;

@Controller
public class AbstractVoteController {

    @Autowired
    private VoteService service;

    public Vote get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public List<VoteTo> getAll() {
        int userId = AuthorizedUser.id();
        return VotesUtil.getAll(service.getAll(userId));
    }

    public Vote create(Vote vote) {
        int userId = AuthorizedUser.id();
        checkNew(vote);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(vote, id);
        service.update(vote, userId);
    }

}
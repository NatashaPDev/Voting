package ru.natashapetrenko.voting.util;

import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.to.VoteTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class VotesUtil {

    private VotesUtil() {
    }

    public static List<VoteTo> getAll(Collection<Vote> votes) {

        return votes.stream()
                .map(VotesUtil::createVoteTO)
                .collect(toList());
    }

    private static VoteTo createVoteTO(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDate(), vote.getTime(), vote.getRestaurant().getId(), vote.getUser().getId());
    }

}
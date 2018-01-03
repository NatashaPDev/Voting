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
                .map(vote -> createVoteTO(vote))
                .collect(toList());
    }

    private static VoteTo createVoteTO(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDateTime(), vote.getRestaurant().getName());
    }

}
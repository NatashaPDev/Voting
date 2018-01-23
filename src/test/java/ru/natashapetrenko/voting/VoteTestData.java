package ru.natashapetrenko.voting;

import ru.natashapetrenko.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.natashapetrenko.voting.RestaurantTestData.RESTAURANT1;
import static ru.natashapetrenko.voting.RestaurantTestData.RESTAURANT2;
import static ru.natashapetrenko.voting.UserTestData.ADMIN;
import static ru.natashapetrenko.voting.UserTestData.USER;
import static ru.natashapetrenko.voting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    private static final int VOTE1_ID = START_SEQ + 17;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2018, Month.JANUARY, 1), LocalTime.of(10, 0), RESTAURANT1, USER);
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, LocalDate.of(2018, Month.JANUARY, 1), LocalTime.of(10, 0), RESTAURANT1, ADMIN);
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, LocalDate.of(2018, Month.JANUARY, 2), LocalTime.of(10, 0), RESTAURANT1, USER);
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, LocalDate.of(2018, Month.JANUARY, 2), LocalTime.of(10, 0), RESTAURANT2, ADMIN);


    public static final List<Vote> VOTES = Arrays.asList(VOTE3, VOTE4, VOTE1, VOTE2);

    public static Vote getCreated() {
        return new Vote(null, LocalDate.of(2018, Month.JANUARY, 3), LocalTime.of(10, 0), RESTAURANT1, USER);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, VOTE1.getDate(), VOTE1.getTime(), RESTAURANT1, USER);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "restaurant").isEqualTo(expected);
    }
}

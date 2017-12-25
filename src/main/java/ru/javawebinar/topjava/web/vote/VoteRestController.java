package ru.javawebinar.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.service.DishService;
import ru.javawebinar.topjava.service.VoteService;
import ru.javawebinar.topjava.to.DishWithExceed;
import ru.javawebinar.topjava.to.VoteTO;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DishesUtil;
import ru.javawebinar.topjava.util.VotesUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class VoteRestController {
    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    private final VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

    public Vote get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public List<VoteTO> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return VotesUtil.getAll(service.getAll(userId));
    }

    public Vote create(Vote vote) {
        int userId = AuthorizedUser.id();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        service.update(vote, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
//    public List<DishWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
//        int userId = AuthorizedUser.id();
//        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
//
//        List<Dish> dishesDateFiltered = service.getBetweenDates(
//                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
//                endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId);
//
//        return DishesUtil.getFilteredWithExceeded(dishesDateFiltered,
//                startTime != null ? startTime : LocalTime.MIN,
//                endTime != null ? endTime : LocalTime.MAX,
//                AuthorizedUser.getPricePerDay()
//        );
//    }
}
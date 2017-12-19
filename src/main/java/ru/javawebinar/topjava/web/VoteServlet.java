package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.vote.VoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

public class VoteServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private VoteRestController voteController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        voteController = springContext.getBean(VoteRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Vote vote = new Vote(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("restaurant"));

            if (request.getParameter("id").isEmpty()) {
                voteController.create(vote);
            } else {
                voteController.update(vote, getId(request));
            }
            response.sendRedirect("votes");

        } else if ("filter".equals(action)) {
//            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
//            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
//            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
//            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
//            request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
//            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                voteController.delete(id);
                response.sendRedirect("votes");
                break;
            case "create":
            case "update":
                final Vote vote = "create".equals(action) ?
                        new Vote(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),  "") :
                        voteController.get(getId(request));
                request.setAttribute("vote", vote);
                request.getRequestDispatcher("/voteForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("votes", voteController.getAll());
                request.getRequestDispatcher("/votes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

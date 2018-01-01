package ru.natashapetrenko.voting.web.dish;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.natashapetrenko.voting.model.Dish;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.natashapetrenko.voting.util.DateTimeUtil.parseLocalDate;
import static ru.natashapetrenko.voting.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/dishes")
public class JspDishController extends AbstractDishController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/dishes";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("dish", super.get(getId(request)));
        return "dishForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("dish", new Dish(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000, ""));
        return "dishForm";
    }

    @PostMapping("/update")
    public String updateOrCreate(HttpServletRequest request) {
        Dish dish = new Dish(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("price")),
                request.getParameter("restaurant"));

        if (request.getParameter("id").isEmpty()) {
            super.create(dish);
        } else {
            super.update(dish, getId(request));
        }
        return "redirect:/dishes";
    }

    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        model.addAttribute("dishes", super.getBetween(startDate));
        return "dishes";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

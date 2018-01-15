package ru.natashapetrenko.voting.web.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.natashapetrenko.voting.TestUtil;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.service.DishService;
import ru.natashapetrenko.voting.util.DishesUtil;
import ru.natashapetrenko.voting.web.AbstractControllerTest;
import ru.natashapetrenko.voting.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.natashapetrenko.voting.DishTestData.*;
import static ru.natashapetrenko.voting.RestaurantTestData.RESTAURANT1_ID;
import static ru.natashapetrenko.voting.TestUtil.contentJson;
import static ru.natashapetrenko.voting.TestUtil.userHttpBasic;
import static ru.natashapetrenko.voting.UserTestData.ADMIN;
import static ru.natashapetrenko.voting.UserTestData.USER;

public class RestaurantDishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantDishRestController.REST_URL.replace("{restaurant_id}", String.valueOf(RESTAURANT1_ID)) + '/';

    @Autowired
    private DishService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishesUtil.asTo(DISH1)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        assertMatch(service.getAll(), DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();

        mockMvc.perform(put(REST_URL + DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(DISH1_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created)));

        Dish returned = TestUtil.readFromJson(action, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13, created);
    }

    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("date", "2018-01-01")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishesUtil.getAll(Arrays.asList(DISH1, DISH2, DISH3, DISH4))));
    }

}

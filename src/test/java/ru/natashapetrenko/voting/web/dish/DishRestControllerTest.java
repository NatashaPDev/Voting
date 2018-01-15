package ru.natashapetrenko.voting.web.dish;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.natashapetrenko.voting.util.DishesUtil;
import ru.natashapetrenko.voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.natashapetrenko.voting.DishTestData.DISHES;
import static ru.natashapetrenko.voting.TestUtil.contentJson;
import static ru.natashapetrenko.voting.TestUtil.userHttpBasic;
import static ru.natashapetrenko.voting.UserTestData.ADMIN;

public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Test
    public void testGetAll() throws Exception {

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishesUtil.getAll(DISHES)));
    }

}

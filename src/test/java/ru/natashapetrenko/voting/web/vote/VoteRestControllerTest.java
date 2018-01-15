package ru.natashapetrenko.voting.web.vote;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.natashapetrenko.voting.TestUtil;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.service.VoteService;
import ru.natashapetrenko.voting.util.VotesUtil;
import ru.natashapetrenko.voting.web.AbstractControllerTest;
import ru.natashapetrenko.voting.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.natashapetrenko.voting.TestUtil.contentJson;
import static ru.natashapetrenko.voting.TestUtil.userHttpBasic;
import static ru.natashapetrenko.voting.UserTestData.ADMIN;
import static ru.natashapetrenko.voting.VoteTestData.*;

public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    public void testCreate() throws Exception {
        Vote created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(created)));

        Vote returned = TestUtil.readFromJson(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), created, VOTE3, VOTE4, VOTE1, VOTE2);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VotesUtil.getAll(Arrays.asList(VOTE1, VOTE2, VOTE3, VOTE4))));
    }

}

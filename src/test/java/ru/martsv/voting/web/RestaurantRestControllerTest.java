package ru.martsv.voting.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.martsv.voting.TestUtil;
import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.service.RestaurantService;
import ru.martsv.voting.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.martsv.voting.RestaurantTestData.*;
import static ru.martsv.voting.TestUtil.userHttpBasic;
import static ru.martsv.voting.UserTestData.ADMIN;
import static ru.martsv.voting.UserTestData.USER1;

/**
 * mart
 * 21.08.2016
 */
public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT4, RESTAURANT3, RESTAURANT2), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    public void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(RESTAURANT1_ID));
    }

    @Test
    public void testUpdateForbidden() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Restaurant returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, RESTAURANT4, RESTAURANT3, RESTAURANT2, RESTAURANT1), service.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(RESTAURANT4, RESTAURANT3, RESTAURANT2, RESTAURANT1)));
    }

    @Test
    public void testGetVotesOnDate() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/votes?date=2016-08-21")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetWinnersOnDate() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "/winners?date=2016-08-20")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(RESTAURANT2)));
    }

}

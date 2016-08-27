package ru.martsv.voting.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.martsv.voting.model.Menu;
import ru.martsv.voting.service.MenuService;
import ru.martsv.voting.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.martsv.voting.MenuTestData.*;
import static ru.martsv.voting.RestaurantTestData.RESTAURANT1_ID;

/**
 * mart
 * 24.08.2016
 */
public class MenuRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    private MenuService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + REST1_MENU1_ID, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(REST1_MENU1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + REST1_MENU1_ID, 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + REST1_MENU1_ID, 1))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + REST1_MENU1_ID, RESTAURANT1_ID))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(REST1_MENU2, REST1_MENU3, REST1_MENU4), service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = getUpdated();

        mockMvc.perform(put(REST_URL + REST1_MENU1_ID, RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(REST1_MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Menu created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Menu returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, REST1_MENU1, REST1_MENU2, REST1_MENU3, REST1_MENU4), service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(REST1_MENU));
    }

    @Test
    public void testGetOnDate() throws Exception {
        mockMvc.perform(get(REST_URL + "ondate?date=2016-08-20", RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER.contentListMatcher(REST1_MENU3, REST1_MENU4));
    }

    @Test
    public void testDeleteOnDate() throws Exception {
        mockMvc.perform(delete(REST_URL + "ondate?date=2016-08-21", RESTAURANT1_ID))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(REST1_MENU3, REST1_MENU4), service.getAll(RESTAURANT1_ID));
    }

}

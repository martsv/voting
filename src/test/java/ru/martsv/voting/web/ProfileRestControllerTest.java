package ru.martsv.voting.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.martsv.voting.TestUtil;
import ru.martsv.voting.model.User;
import ru.martsv.voting.to.UserTo;
import ru.martsv.voting.util.UserUtil;
import ru.martsv.voting.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.martsv.voting.TestUtil.userHttpBasic;
import static ru.martsv.voting.UserTestData.*;
import static ru.martsv.voting.web.ProfileRestController.REST_URL;

/**
 * mart
 * 29.08.2016
 */
public class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER1)));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER2, USER3), userService.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER1))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isOk());

        User expected = new User(USER1);
        UserUtil.updateFromTo(expected, updatedTo);
        MATCHER.assertEquals(expected, userService.getByEmail("newemail@ya.ru"));
    }

    @Test
    public void testUpdateNotValid() throws Exception {
        UserTo updatedTo = new UserTo(null, null, "password", null);

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER1))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
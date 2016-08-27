package ru.martsv.voting.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.martsv.voting.model.Menu;
import ru.martsv.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static ru.martsv.voting.MenuTestData.*;
import static ru.martsv.voting.RestaurantTestData.RESTAURANT1_ID;

/**
 * mart
 * 23.08.2016
 */
public class DataJpaMenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(REST1_MENU1_ID, RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(REST1_MENU2, REST1_MENU3, REST1_MENU4), service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(REST1_MENU1_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Menu created = getCreated();
        service.save(created, RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, REST1_MENU1, REST1_MENU2, REST1_MENU3, REST1_MENU4), service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGet() throws Exception {
        Menu actual = service.get(REST1_MENU1_ID, RESTAURANT1_ID);
        MATCHER.assertEquals(REST1_MENU1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(REST2_MENU1_ID, RESTAURANT1_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        MATCHER.assertEquals(updated, service.get(REST1_MENU1_ID, RESTAURANT1_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        Menu item = service.get(REST1_MENU1_ID, RESTAURANT1_ID);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + REST1_MENU1_ID);
        service.update(item, RESTAURANT1_ID + 1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(REST1_MENU, service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetOnDate() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(REST1_MENU3, REST1_MENU4),
                service.getOnDate(LocalDate.of(2016, Month.AUGUST, 20), RESTAURANT1_ID));
    }

    @Test
    public void testDeleteForDate() throws Exception {
        service.deleteOnDate(LocalDate.of(2016, Month.AUGUST, 21), RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(REST1_MENU3, REST1_MENU4), service.getAll(RESTAURANT1_ID));
    }
}

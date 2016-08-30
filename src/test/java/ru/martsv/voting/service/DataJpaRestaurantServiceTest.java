package ru.martsv.voting.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static ru.martsv.voting.RestaurantTestData.*;

/**
 * mart
 * 20.08.2016
 */
public class DataJpaRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT4, RESTAURANT3, RESTAURANT2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testSave() throws Exception {
        Restaurant created = getCreated();
        service.save(created);
        MATCHER.assertCollectionEquals(Arrays.asList(created, RESTAURANT4, RESTAURANT3, RESTAURANT2, RESTAURANT1), service.getAll());
    }

    @Test
    public void testGet() throws Exception {
        Restaurant actual = service.get(RESTAURANT1_ID);
        MATCHER.assertEquals(RESTAURANT1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(RESTAURANT1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(RESTAURANTS, service.getAll());
    }

    @Test
    public void testGetVotesOnDate() throws Exception {
        assertEquals(2, service.getVotesOnDate(RESTAURANT1_ID, LocalDate.of(2016, Month.AUGUST, 21)));
    }

    @Test
    public void testGetWinnersOnDate() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singleton(RESTAURANT1), service.getWinnersOnDate(LocalDate.of(2016, Month.AUGUST, 21)));
    }

}

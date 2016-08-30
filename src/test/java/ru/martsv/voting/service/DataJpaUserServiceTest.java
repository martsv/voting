package ru.martsv.voting.service;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.martsv.voting.model.Role;
import ru.martsv.voting.model.User;
import ru.martsv.voting.repository.JpaUtil;
import ru.martsv.voting.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.martsv.voting.UserTestData.*;

/**
 * mart
 * 28.08.2016
 */
public class DataJpaUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaUtil jpaUtil;

    @After
    public void tearDown() throws Exception {
        service.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, Role.ROLE_USER);
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2, USER3, newUser), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "bill@microsoft.com", "newPass", Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER2, USER3), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(USER1, service.get(USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        MATCHER.assertEquals(USER3, service.getByEmail("larry@oracle.com"));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2, USER3), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setRole(Role.ROLE_ADMIN);
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER1_ID));
    }
}

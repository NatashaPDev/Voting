package ru.natashapetrenko.voting.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.natashapetrenko.voting.model.Role;
import ru.natashapetrenko.voting.model.User;
import ru.natashapetrenko.voting.repository.JpaUtil;
import ru.natashapetrenko.voting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.natashapetrenko.voting.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void setUp() {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
    }

    @Test
    public void create() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() {
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1);
    }

    @Test
    public void get() {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        assertMatch(user, ADMIN);
    }

    @Test
    public void update() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

    @Test
    public void testEnable() {
        service.enable(USER_ID, false);
        Assert.assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        Assert.assertTrue(service.get(USER_ID).isEnabled());
    }
}
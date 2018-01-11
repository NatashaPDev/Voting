package ru.natashapetrenko.voting.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.natashapetrenko.voting.model.Role;
import ru.natashapetrenko.voting.model.User;
import ru.natashapetrenko.voting.repository.JpaUtil;

import javax.validation.ConstraintViolationException;

abstract public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {

    @Autowired
    private JpaUtil jpaUtil;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
    }
}
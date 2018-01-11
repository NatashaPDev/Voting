package ru.natashapetrenko.voting.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.natashapetrenko.voting.model.Restaurant;
import ru.natashapetrenko.voting.repository.JpaUtil;

import javax.validation.ConstraintViolationException;

abstract public class AbstractJpaRestaurantServiceTest extends AbstractRestaurantServiceTest {

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
        validateRootCause(() -> service.create(new Restaurant(null, "  ")), ConstraintViolationException.class);
    }
}
package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(MealServiceTest.class);

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(USER_MEAL, USER_ID);
        Meal expected = USER_MEALS.get(0);
        assertEquals(expected, actual);
        actual = service.get(ADMIN_MEAL, ADMIN_ID);
        expected = ADMIN_MEALS.get(0);
        LOG.debug("expected = {}, actual={}", expected, actual);
        assertEquals(expected, actual);
    }

    @Test(expected = NotFoundException.class)
    public void getWithForeignMeal() throws NotFoundException {
        service.get(USER_MEAL, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(DELETED_MEAL, ADMIN_ID);
        List<Meal> actual = service.getAll(ADMIN_ID);
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(ADMIN_MEALS);
    }
    @Test(expected = NotFoundException.class)
    public void deleteWithForeignMeal() throws NotFoundException {
        service.delete(DELETED_MEAL, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> actual = service.getBetweenDates(START_DATE.toLocalDate(), END_DATE.toLocalDate(), USER_ID);
        List<Meal> expected = USER_MEALS.stream()
                .sorted(Comparator.comparingInt(Meal::getId).reversed())
                .collect(Collectors.toList()).subList(3,6);
        LOG.debug("\n actual meals {} \n expected meals {}", actual, expected);
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        List<Meal> expected = USER_MEALS.stream().sorted(Comparator.comparingInt(Meal::getId).reversed()).collect(Collectors.toList());
        LOG.debug("\n actual meals {} \n expected meals {}", actual, expected);
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    @Test
    public void update() {
        Meal actual = new Meal(MEAL);
        actual.setId(USER_MEAL);
        service.update(actual, USER_ID);
        Meal expected = service.get(USER_MEAL, USER_ID);
        LOG.debug("expected = {}, actual = {}", expected, actual);
        assertEquals(service.get(USER_MEAL, USER_ID), actual);
    }

    @Test (expected = NotFoundException.class)
    public void updateWithForeignMeal() throws NotFoundException {
        Meal updated = new Meal(MEAL);
        updated.setId(USER_MEAL);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal created = new Meal(MEAL);
        Meal actual = service.create(created, USER_ID);
        created.setId(++ID);
        LOG.debug("created = {}, actual = {}", created, actual);
        assertEquals(created, actual);
    }

    @Test(expected = DuplicateKeyException.class)
    public void createWithNonuniqueDate() {
        Meal created = USER_MEALS.get(0);
        created.setId(null);
        service.create(created, USER_ID);
    }
}
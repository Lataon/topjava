package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create (Meal meal) {
        LOG.debug("create {}", meal);
        return service.create(authUserId(), meal);
    }

    public void update (Meal meal) throws NotFoundException {
        LOG.debug("update {}", meal);
       service.update(authUserId(), meal);
    }

    public void delete(int mealId) throws NotFoundException {
        LOG.debug("delete {}", mealId);
        service.delete(authUserId(), mealId);
    }

    public Meal get(int mealId) throws NotFoundException {
        LOG.debug("get {}", mealId);
        return service.get(authUserId(), mealId);
    }

    public List<Meal> getAll() {
        LOG.debug("get all");
        return service.getAll(authUserId());
    }

    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LOG.debug("get between");
        return service.getBetween(startDateTime, endDateTime, authUserId());
    }
}
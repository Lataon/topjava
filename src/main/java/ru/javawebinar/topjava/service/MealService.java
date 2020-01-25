package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create (int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public void update (int userId, Meal meal) throws NotFoundException {
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

    public void delete(int userId, int mealId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    public Meal get(int userId, int mealId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Meal> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        return repository.getBetween(startDate, startTime, endDate, endTime, userId);
    }
}
package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.Memory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        LOG.debug("initiation meal repository map");
        Memory.MEALSFor1.forEach(m->save(1, m));
        Memory.MEALSFor2.forEach(m->save(2, m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        LOG.debug("save meal {}, user id  {}", meal, userId);
            Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                meals.put(meal.getId(), meal);
                return meal;
            }
            return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }

    @Override
    public boolean delete(int userId, int mealId) {
        LOG.debug("delete meal id {}, user id  {}", mealId, userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(mealId)!=null;
    }

    @Override
    public Meal get(int userId, int mealId) {
        LOG.debug("get meal id {}, user id  {}", mealId, userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals!=null ? meals.get(mealId) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        LOG.debug("get all for user id {}", userId);
        return getAllFiltered(userId, meal -> true);
    }

    @Override
     public List<Meal> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        LOG.debug("get all between date {} and {} and between time {} and {} for user id {}", startDate, endDate, startTime, endTime, userId);
        return getAllFiltered(userId, meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)&&DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
        }

     private List<Meal> getAllFiltered(int userId, Predicate<Meal> filter) {
         Map<Integer, Meal> meals = repository.get(userId);
         return CollectionUtils.isEmpty(meals) ? Collections.emptyList() :
                meals.values().stream()
                        .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
         }
}


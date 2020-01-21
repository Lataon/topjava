package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.Month;
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

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );
    {
        LOG.debug("initiation meal repository map");
        MEALS.forEach(m->save(1, m));
        MEALS.forEach(m->save(2, m));
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
     public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        LOG.debug("get all between {} and {} for user id {}", startDateTime, endDateTime, userId);
        return getAllFiltered(userId, meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime));
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


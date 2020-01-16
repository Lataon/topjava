package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsData;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private MealsData mealsData = new MealsData();
    private static AtomicInteger atomicId = new AtomicInteger(100000);

    @Override
    public void deleteMeal(int mealId) {
        List<Meal> meals = mealsData.getMeals();
        meals.remove(getMealById(mealId));
    }

    @Override
    public void addOrUpdateMeal(Meal meal) {
        if (meal.getId()==null) {
            atomicId.addAndGet(1);
            meal.setId(atomicId.get());
            List<Meal> meals = mealsData.getMeals();
            meals.add(meal);
        }
        else {
            Meal current = getMealById(meal.getId());
            current.setDateTime(meal.getDateTime());
            current.setDescription(meal.getDescription());
            current.setCalories(meal.getCalories());
        }
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealsData.getMeals();
    }

    @Override
    public Meal getMealById(int mealId) {
        List<Meal> meals = mealsData.getMeals();
        return meals.stream().filter(m->m.getId()==mealId).findFirst().get();
    }
}

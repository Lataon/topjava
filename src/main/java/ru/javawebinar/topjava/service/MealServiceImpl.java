package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImpl implements MealService{
    private MealDao mealDao = new MealDaoImpl();
    @Override
    public void deleteMeal(int mealId) {
        mealDao.deleteMeal(mealId);
    }

    @Override
    public void addMeal(Meal meal) {
        mealDao.addOrUpdateMeal(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealDao.addOrUpdateMeal(meal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealDao.getAllMeals();
    }

    @Override
    public Meal getMealById(int mealId) {
        return mealDao.getMealById(mealId);
    }
}

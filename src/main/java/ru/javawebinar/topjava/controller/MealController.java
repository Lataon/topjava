package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import java.util.List;

public class MealController {

    private MealService mealService = new MealServiceImpl();
    public List<Meal> getAll () {
        return mealService.getAllMeals();
    }

    public void deleteMeal(int mealId) {
        mealService.deleteMeal(mealId);
    }

    public void addMeal(Meal meal) {
        mealService.addMeal(meal);
    }

    public void updateMeal(Meal meal) {
        mealService.updateMeal(meal);
    }

    public Meal getMealById(int mealId) {
        return mealService.getMealById(mealId);
    }

}

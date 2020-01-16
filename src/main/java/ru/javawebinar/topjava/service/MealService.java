package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    void deleteMeal(int mealId);
    void addMeal(Meal meal);
    void updateMeal(Meal meal);
    List<Meal> getAllMeals();
    Meal getMealById(int mealId);
}

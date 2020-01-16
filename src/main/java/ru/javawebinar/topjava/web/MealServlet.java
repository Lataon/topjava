package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.controller.MealController;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private static final int DEFAULT_CALORIES_PER_DAY = 2000;
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealController mealController = new MealController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        List<MealTo> mealsTo = MealsUtil.getAll(mealController.getAll(), DEFAULT_CALORIES_PER_DAY);
        String forward="";
        String action = request.getParameter("action");
        if (action!=null) {
        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealController.deleteMeal(mealId);
            forward = LIST_MEAL;
            mealsTo = MealsUtil.getAll(mealController.getAll(), DEFAULT_CALORIES_PER_DAY);
            request.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("edit")){
            LOG.debug("redirect to update page");
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealController.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            forward = LIST_MEAL;
            request.setAttribute("meals", mealsTo);
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        }
        else {
            request.setAttribute("meals", mealsTo);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("do Post");
        request.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(dateTime, description, calories);
        String mealId = request.getParameter("mealId");

        if(mealId == null || mealId.isEmpty())
        {
            mealController.addMeal(meal);
        }
        else
        {
            meal.setId(Integer.parseInt(mealId));
            mealController.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        List<MealTo> mealsTo = MealsUtil.getAll(mealController.getAll(), DEFAULT_CALORIES_PER_DAY);
        request.setAttribute("meals", mealsTo);
        view.forward(request, response);
    }
}

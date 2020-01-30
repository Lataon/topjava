package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static int ID = START_SEQ + 2;
    public static final int USER_MEAL = START_SEQ + 2;
    public static final int ADMIN_MEAL = START_SEQ + 8;
    public static final int DELETED_MEAL = START_SEQ + 9;
    public static final LocalDateTime START_DATE = LocalDateTime.of(2015, Month.MAY, 30, 10, 0);
    public static final LocalDateTime END_DATE = LocalDateTime.of(2015, Month.MAY, 30, 13, 0);

    public static final List<Meal> USER_MEALS= Arrays.asList(
            new Meal(ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(++ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(++ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(++ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(++ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(++ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static final List<Meal> ADMIN_MEALS= Arrays.asList(
            new Meal(++ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510)
    );

    public static final Meal MEAL = new Meal (LocalDateTime.of(2016, Month.MAY, 31, 21, 0), "EXPECTED", 510);

    }

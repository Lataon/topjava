package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.abstracts.AbstractMealServiceTest;

@ActiveProfiles(profiles = Profiles.JPA)
public class JPAMealTest extends AbstractMealServiceTest {
}

package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.abstracts.AbstractUserServiceTest;

@ActiveProfiles(profiles = Profiles.DATAJPA)
public class DATAJPAUserTest extends AbstractUserServiceTest {
}

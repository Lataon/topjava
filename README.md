Java Enterprise Online Project 
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

![topjava_structure](https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg)

    Когда вы слышите что-то, вы забываете это.
    Когда вы видите что-то, вы запоминаете это.
    Но только когда вы начинаете делать это,
    вы начинаете понимать это

    Старинная китайская поговорка

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW03
- 1 Понять, почему перестали работать `SpringMain, InMemoryAdminRestControllerTest, InMemoryAdminRestControllerSpringTest`
- 2 Дополнить скрипты создания и инициализации базы таблицой MEALS. Запустить скрипты на вашу базу (через Run). Порядок таблиц при DROP и DELETE важен, если они связаны внешними ключами (foreign key, fk). Проверьте, что ваши скрипты работают
- 3 Реализовать через Spring JDBC Template `JdbcMealRepository`
  - 3.1. сделать каждый метод за один SQL запрос
  - 3.2. `userId` в класс `Meal` вставлять НЕ надо (для UI и REST это лишние данные, userId это id залогиненного пользователя)
  - 3.3. `JbdcTemplate` работает через сеттеры. Вместе с конструктором по умолчанию их нужно добавить в `Meal` 
  - 3.4. Cписок еды должен быть отсортирован (тогда мы его сможем сравнивать с тестовыми данными). Кроме того это требуется для UI и API: последняя еда наверху.
- 4 Проверить работу MealServlet, запустив приложение

### Optional
- 5 Сделать `MealServiceTest` из `MealService` и реализовать тесты для `JdbcMealRepository`.
> По `Ctrl+Shift+T` (выбрать JUnit4) можно создать тест для конкретного класса, выбрав для него нужные методы. Тестовый класс создастся в папке `test` в том же пакете, что и тестируемый. 
  - 5.1 Сделать тестовые данные `MealTestData` (точно такие же, как вставляем в `populateDB.sql`).
  - 5.2 Сделать тесты на чужую еду (delete, get, update) с тем чтобы получить `NotFoundException`.
- 6 Почнинить `SpringMain, InMemory*Test`. **InMemory тесты должны использовать реализацию в памяти**
- 7 Сделать индексы к таблице `Meals`: запретить создавать у одного и того-же юзера еду с одинаковой dateTime.
Индекс на pk (id) postgres создает автоматически: <a href="http://stackoverflow.com/questions/970562/postgres-and-indexes-on-foreign-keys-and-primary-keys">Postgres and Indexes on Foreign Keys and Primary Keys</a>

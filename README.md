Java Enterprise Online Project 
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

![topjava_structure](https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg)

    Когда вы слышите что-то, вы забываете это.
    Когда вы видите что-то, вы запоминаете это.
    Но только когда вы начинаете делать это,
    вы начинаете понимать это

    Старинная китайская поговорка

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFZFdWWFdwams0eGM">Домашнее задание HW05</a>

- 1: Имплементировать `DataJpaMealRepository`.
- 2: Разделить реализации Repository по профилям Spring: `jdbc`, `jpa`, `datajpa` (общее в профилях можно объединять, например, `<beans profile="datajpa,jpa">`).
  - 2.1: Профили выбора DB (`postgres/hsqldb`) и реализации репозитория (`jdbc/datajpa/jpa`) независимы друг от друга, и при запуске приложения (тестов) нужно задать тот, и другой.
  - 2.2: Для интеграции с IDEA не забудьте выставить в `spring-db.xml` справа вверху в `Change Profiles...` профили, например, `datajpa, postgres`.
  - 2.3: Общие части для всех в `spring-db.xml` можно оставить как есть без профилей вверху файла **(до первого `<beans profile=` !!!)**.
- 3: Сделать тесты всех реализаций (`jdbc, jpa, datajpa`) через наследование (без дублирования).
  -  3.1 **сделать один базовый класс для `MealServiceTest` и `UserServiceTest`**.
  -  3.2 сводку по времени выполнения тестов также сделать для `user`
- 4: Проверить запуск всех тестов: `mvn test` (в IDEA Maven Lifecycle - `test`, кнопку `skipTest` отжать).

### Optional

- 5: Разделить `JdbcMealRepository` для HSQLDB (она не умеет работать с Java8 Time API) и Postgres через `@Profile` (для Postgres оставить `LocalDateTime`). 
  - Цель задания - потренироваться с [паттерном "шаблонный метод"](https://refactoring.guru/ru/design-patterns/template-method) и профилями Spring. 
Какие бины Spring попадут в контекст зависит от выставления активных профилей при запуске (`@ActiveProfiles` в тестах) и конфигурации, где задаются бины для каждого профиля.
Абстрактные классы не создаются и в контекст не попадают. 
  - После выполнения разделения на основе профилей, можно предложить решение проще.
- 6: Починить `MealServlet` и использовать в `SpringMain` реализацию DB: добавить профили. Попробуйте поднять Spring контекст без использования `spring.profiles.active`.
- 7: Сделать и протестировать в сервисах методы (тесты и реализация - только для `DataJpa`):
  - 7.1:  достать по `id` пользователя вместе с его едой
  - 7.2:  достать по `id` и `userId`  еду вместе с пользователем
  - 7.3:  обращения к DB сделать в одной транзакции (можно сделать разные варианты). <a href="https://en.wikibooks.org/wiki/Java_Persistence/OneToMany">Java Persistence/OneToMany</a>

Java Enterprise Online Project 
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

![topjava_structure](https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg)

    Когда вы слышите что-то, вы забываете это.
    Когда вы видите что-то, вы запоминаете это.
    Но только когда вы начинаете делать это,
    вы начинаете понимать это

    Старинная китайская поговорка

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW07

- 1: Добавить тесты контроллеров:
  - 1.1 `RootControllerTest.testMeals` для `meals.jsp`
  - 1.2 `ResourceControllerTest` для `style.css` (проверить `status` и `ContentType`)
- 2: Реализовать `MealRestController` и протестировать его через `MealRestControllerTest`
  - 2.1 следите, чтобы url в тестах совпадал с параметрами в методе контроллера. Можно добавить логирование `<logger name="org.springframework.web" level="debug"/>` для проверки маршрутизации.
  - 2.2 в параметрах `getBetween` принимать `LocalDateTime` (конвертировать через <a href="http://blog.codeleak.pl/2014/06/spring-4-datetimeformat-with-java-8.html">@DATETIMEFORMAT WITH JAVA 8 DATE-TIME API</a>), а передавать в тестах в формате `ISO_LOCAL_DATE_TIME` (например `'2011-12-03T10:15:30'`). Вызывать `super.getBetween()` пока без проверки на `null`, используя `toLocalDate()/toLocalTime()` (см. Optional п.3)

### Optional
- 3: Переделать `MealRestController.getBetween` на параметры `LocalDate/LocalTime` c раздельной фильтрацией по времени/дате, работающий при `null` значениях (см. демо и `JspMealController.getBetween`). Заменить `@DateTimeFormat` на свои LocalDate/LocalTime конверторы или форматтеры.
  -  <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#core-convert">Spring Type Conversion</a>
  -  <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#format">Spring Field Formatting</a>
  -  <a href="http://stackoverflow.com/questions/13048368/difference-between-spring-mvc-formatters-and-converters">Difference between Spring MVC formatters and converters</a>
- 4: Протестировать `MealRestController` (SoapUi, curl, IDEA Test RESTful Web Service, Postman). Запросы `curl` занести в отдельный `md` файл (либо `README.md`)
  
**На следующем занятии используется JavaScript/jQuery. Если у вас там пробелы, <a href="https://github.com/JavaOPs/topjava#html-javascript-css">пройдите его основы</a>**

### Optional 2
- 5: попробовать отключить кэш в тестах через `NoOpCacheManager` (все тесты должны проходить). 
  - [Spring Cache and Integration Testing](https://dzone.com/articles/spring-cache-and-integration-testing)
  - [JPA 2.0 disable session cache for unit tests](https://stackoverflow.com/a/58963737/548473)
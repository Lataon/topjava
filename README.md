Java Enterprise Online Project 
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

![topjava_structure](https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg)

    Когда вы слышите что-то, вы забываете это.
    Когда вы видите что-то, вы запоминаете это.
    Но только когда вы начинаете делать это,
    вы начинаете понимать это

    Старинная китайская поговорка
## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW01 (делаем ветку HW01 от последнего патча в master) 

#### 1. Реализовать сервлет с отображением в таблице списка еды (в памяти и БЕЗ учета пользователя)

> Деплоиться в Tomcat лучше как `war exploded`: нет упаковки в war и при нажатой кнопке `Update Resources on Frame Deactivation` можно обновляться css, html, jsp без передеплоя. При изменении `web.xml`, добавлении методов, классов необходим redeploy.

- 1.1 По аналогии с `UserServlet` добавить `MealServlet` и `meals.jsp`
  - Задеплоить приложение (war) в Tomcat c `applicationContext=topjava` (приложение должно быть доступно по <a href="http://localhost:8080/topjava">http://localhost:8080/topjava</a>)
  - Попробовать разные деплои в Tomcat, remote и local debug
- 1.2 Сделать отображения списка еды в JSP, цвет записи в таблице зависит от параметра `excess` (красный/зеленый).
  - 1.2.1 Список еды захардкодить (те проинициализировать в коде, желательно чтобы в проекте инициализация была только в одном месте)
  - 1.2.2 Время выводить без 'T'
  - 1.2.3 Список выводим БЕЗ фильтрации по `startTime/endTime`
  - 1.2.4 С обработкой исключений пока можно не заморачиваться, мы будем красиво обрабатывать в конце стажировки
  - 1.2.5 Вариант реализации:
    - из сервлета преобразуете еду в `List<MealTo>`;
    - кладете список в запрос (`request.setAttribute`);
    - делаете `forward` на jsp для отрисовки таблицы (при `redirect` атрибуты теряются).
    - в `JSP` для цикла можно использовать `JSTL tag forEach`. Для подключения `JSTL` в `pom.xml` и шапку JSP нужно добавить:

### Optional
#### 2. Реализуем в ПАМЯТИ CRUD (create/read/update/delete) для еды
**Пример: <a href="http://danielniko.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/">Simple CRUD using Servlet/JSP</a>**
> - Пример нужно САМОСТОЯТЕЛЬНО переделать: вместо хранения в MySql нужно хранить в памяти (задание упрощается).
> - Классы: сервлет, инрерфейс хранения, его реализация для хранения в памяти
- 2.1 Хранение в памяти будет одна из наших CRUD реализаций (позже будет JDBC, JPA и DATA-JPA).
- 2.2 Работать с реализацией CRUD через интерфейс, который не должен ничего знать о деталях реализации (Map, DB или что-то еще).
- 2.3 Добавить поле `id` в `Meal/ MealTo` и реализовать генерацию счетчика, УЧЕСТЬ МНОГОПОТОЧНОСТЬ СЕРВЛЕТОВ
    - [обзор java.util.concurrent](https://habrahabr.ru/company/luxoft/blog/157273/)
- 2.4 Сделать форму редактирования в JSP: AJAX/JavaScript использовать НЕ надо, делаем через `<form method="post">` и `doPost()` в сервлете.
- 2.5 Для ввода дат и времени можно использовать <a href="https://webref.ru/html/input/type">html5 типы</a>, хотя они поддерживаются не всеми браузерами (<a href="https://robertnyman.com/html5/forms/input-types.html">протестировать свой браузер</a>). В конце курса мы добавим <a href="http://xdsoft.net/jqplugins/datetimepicker/">DateTimePicker jQuery plugin</a>, который будет работать на всех браузерах.



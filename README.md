Java Enterprise Online Project 
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

![topjava_structure](https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg)

    Когда вы слышите что-то, вы забываете это.
    Когда вы видите что-то, вы запоминаете это.
    Но только когда вы начинаете делать это,
    вы начинаете понимать это

    Старинная китайская поговорка


## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW0
```
Реализовать метод `UserMealsUtil.filteredByCycles` через циклы (`forEach`):
-  должны возвращаться только записи между `startTime` и `endTime`
-  поле `UserMealWithExcess.excess` должно показывать, 
                                     превышает ли сумма калорий за весь день значение `caloriesPerDay`
        
Т.е `UserMealWithExcess` - это запись одной еды, но поле `excess` будет одинаково для всех записей за этот день.
    
- Проверьте результат выполнения ДЗ (можно проверить логику в http://topjava.herokuapp.com , список еды)
- Оцените Time complexity алгоритма. Если она больше O(N), например O(N*N) или N*log(N), сделайте O(N).
```

### Optional (Java 8 Stream API)
```
Реализовать метод `UserMealsUtil.filteredByStreams` через Java 8 Stream API.
```

### Optional 2 (+5 бонусов)
```
Сделать реализацию со сложностью O(N) (обратите внимание на п.13 замечаний):
- циклом за 1 проход по List<UserMeal>
  - без создания копий списка (в том числе модифицированных) и без дополнительных проходов по частям списка
- через Stream API за 1 проход по исходному списку Stream<UserMeal> meals
  - возможно дополнительные проходы по частям списка
  - нельзя использовать внешние коллекции, не являющиеся частью коллектора или результатами работы stream
```


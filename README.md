# T1 Homework3 by Иванов Никита Денисович

Это домашняя работа №3 в [рамках открытых школ T1](https://t1.ru/internship/item/otkrytye-shkoly-ot-holdinga-t1/)

Тз к работе вы можете посмотреть по [ссылке](links/Task.md)

## Как начать

### 1. Настройка базы данных
Вам необходимо проставить значение переменных окружения DATABASE_URL, DATABASE_USERNAME DATABASE_PASSWORD это можно сделать командой:
```bash
export DATABASE_URL=<your database url>
export DATABASE_USERNAME=<your database username>
export DATABASE_PASSWORD=<your database password>
```

### 2. Сборка и запуск приложения

В качестве демонстрации работоспособности разработанного starter-а был разработан модуль проверки (check), в него уже добавлена зависимость:
```xml
<dependency>
<groupId>com.example</groupId>
<artifactId>logger-spring-boot-starter</artifactId>
<version>0.0.1-SNAPSHOT</version>
</dependency>
```
Все что необходимо - это собрать и запустить приложение, перейти на следующую ручку (http://localhost:8081/check/arg) и смотреть что появится в логах.

Инструкция по сборке и запуску приложения (выполнять из корня проекта):
```bash
mvn clean install
java -jar check/target/check-0.0.1-SNAPSHOT.jar
```

## 3. Документация с примерами использования

С документацией разработанного стартера вы можете ознакомиться перейдя по [ссылке](links/Documentation.md)

---
**Примечание:** Этот проект разработан исключительно в учебных и демонстрационных целях.
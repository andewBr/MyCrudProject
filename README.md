# тестовый проект 

## :pushpin: Содержание:

- [Технологии и инструменты](#earth_africa-технологии-и-инструменты)

| Компонент                    | Версия                            |
|------------------------------|-----------------------------------|
| `JDK`                        | 17.х                              |
| `Junit`                      | 5.9.х                             |
| `Вспомогательные компоненты` | Lombok Assertj javafaker mockito  |

## :rocket: Технологии и инструменты

<p align="center">
<a href="https://www.java.com/"><img src="images/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://junit.org/junit5/"><img src="images/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://www.liquibase.com/"><img src="images/liquibase-blue.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://maven.apache.org/"><img src="images/Apache_Maven_logo.svg" width="150" height="50"  alt="Gradle"/></a>
<a href="https://site.mockito.org/"><img src="images/mockito.svg" width="100" height="50"  alt="Gradle"/></a>
</p>

## :construction sign: замечания от Евгения
```
1) в pom.xml вынести версии в properties section
2) create method save
3) вынести connection в утилитный класс и реализовать 2-а метода
4) переделать collection label_post (many to many)
5) analog application context
```

## :computer: Запуск основного приложения
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```



## :computer: Запуск тестов из терминала
```bash
mvn clean test
```


package org.example;


import com.github.javafaker.Faker;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Writer;
import org.example.view.LabelView;
import org.example.view.PostView;
import org.example.view.WriterView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.example.config.ValueForConfig.*;


public class ManualApplicationContextExample {
    public static void main(String[] args) throws SQLException, LiquibaseException {
        // Create an application context
        ApplicationContext context = new ApplicationContext();

        context.registerBean("labelView", new LabelView());
        context.registerBean("postView", new PostView());
        context.registerBean("writerView", new WriterView());

        LabelView labelView = (LabelView) context.getBean("labelView");
        PostView postView = (PostView) context.getBean("postView");
        WriterView writerView = (WriterView) context.getBean("writerView");

        try (Connection connection = DriverManager.getConnection(URL.getValue(), USERNAME.getValue(), PASSWORD.getValue())) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/changelog/db.changelog1.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("");

//         label CRUD
            System.out.println("====================== label CRUD ======================");
            labelView.insert(new Label(new Faker().book().title()));
            labelView.insert(new Label(new Faker().book().title()));
            labelView.insert(new Label(new Faker().book().title()));

            labelView.findById(1);
            labelView.removeById(1);
            System.out.println("============================================");
            labelView.findAll();

//          post CRUD
            System.out.println("====================== post CRUD ======================");
            LocalDateTime currentDateTime = LocalDateTime.now();

            LocalDateTime yesterdayDateTime = currentDateTime.minus(1, ChronoUnit.WEEKS);
            LocalDateTime weeksAgo = currentDateTime.minus(1, ChronoUnit.WEEKS);

            Post post1 = new Post(new Faker().company().name(), currentDateTime, yesterdayDateTime, 2);
            Post post2 = new Post(new Faker().company().name(), currentDateTime, weeksAgo, 2);

            postView.insert(post1, 2);
            postView.insert(post2, 3);
            postView.findById(1);
            postView.removeById(1);
            System.out.println("============================================");
            postView.findAll();

//         Writer CRUD
            System.out.println("====================== Writer CRUD ======================");
            Writer writer1 = new Writer(new Faker().name().firstName(), new Faker().name().lastName(), 2);
            Writer writer2 = new Writer(new Faker().name().firstName(), new Faker().name().lastName(), 2);

            writerView.save(writer1);
            writerView.save(writer2);
            writerView.findById(1);
            writerView.removeById(1);
            System.out.println("============================================");
            writerView.findAll();
        }
    }

}
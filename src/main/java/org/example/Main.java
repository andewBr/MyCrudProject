package org.example;

import com.github.javafaker.Faker;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.config.DatabaseManager;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Writer;
import org.example.repository.LabelRepositoryImpl;
import org.example.repository.PostRepositoryImpl;
import org.example.repository.WriterRepository;
import org.example.repository.WriterRepositoryImpl;
import org.example.service.LabelServiceImpl;
import org.example.service.PostServiceImpl;
import org.example.service.WriterServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.example.config.ValueForConfig.*;

public class Main {

    public static void main(String[] args) throws LiquibaseException, SQLException {

        Connection connection1 = DatabaseManager.getConnection();

        try (Connection connection = DriverManager.getConnection(URL.getValue(), USERNAME.getValue(), PASSWORD.getValue())) {

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/changelog/changelog-master.yaml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("");

            // label CRUD
            System.out.println("====================== label CRUD ======================");
            LabelRepositoryImpl labelRepository = new LabelRepositoryImpl(connection1);
            LabelServiceImpl labelService = new LabelServiceImpl(labelRepository);
            // insert
            labelService.insert(new Label(new Faker().book().title()));
            labelService.insert(new Label(new Faker().book().title()));

            System.out.println("label findById 1:" + labelService.findById(1));
            System.out.println("remove row by id 1: " + labelService.removeById(1));
            System.out.println("============================================");
            System.out.println("find all existing labels: " + labelService.findAll());

            // post CRUD
            System.out.println("====================== post CRUD ======================");
            LocalDateTime currentDateTime = LocalDateTime.now();

            LocalDateTime yesterdayDateTime = currentDateTime.minus(1, ChronoUnit.WEEKS);
            LocalDateTime weeksAgo = currentDateTime.minus(1, ChronoUnit.WEEKS);

            Post post1 = new Post(new Faker().company().name(), currentDateTime, yesterdayDateTime, 2);
            Post post2 = new Post(new Faker().company().name(), currentDateTime, weeksAgo, 2);

            PostRepositoryImpl postRepository = new PostRepositoryImpl(connection1);
            PostServiceImpl postService = new PostServiceImpl(postRepository);
            postService.insert(post1);
            postService.insert(post2);
            System.out.println("post findById 1: " + postService.findById(1));
            System.out.println("remove row by id 1: " + postService.removeById(1));
            System.out.println("============================================");
            System.out.println("find all existing posts: " + postService.findAll());

//         Writer CRUD
            System.out.println("====================== Writer CRUD ======================");
            Writer writer1 = new Writer(new Faker().name().firstName(), new Faker().name().lastName(), 2);
            Writer writer2 = new Writer(new Faker().name().firstName(), new Faker().name().lastName(), 2);

            WriterRepository writerRepository = new WriterRepositoryImpl(connection1);
            WriterServiceImpl writerService = new WriterServiceImpl(writerRepository);
            writerService.save(writer1);
            writerService.save(writer2);
            System.out.println("writer findById 1: " + writerService.findById(1));
            System.out.println("remove row by id 1: " + writerService.removeById(1));
            System.out.println("============================================");
            System.out.println("find all existing writers: " + writerRepository.findAll());
        }
    }
}
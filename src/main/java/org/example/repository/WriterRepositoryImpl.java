package org.example.repository;

import lombok.SneakyThrows;
import org.example.model.Post;
import org.example.model.Writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WriterRepositoryImpl implements WriterRepository {

    private Connection connection;

    public WriterRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    @Override
    public Writer findById(Integer integer) {
        String sql = "select * from writer where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapToResultSetWriter(resultSet);
            }
        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<Writer> findAll() {
        String sql = "select * from writer";
        List<Writer> writers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                writers.add(mapToResultSetWriter(resultSet));
        }
        return writers;
    }

    @SneakyThrows
    @Override
    public String insert(Writer entity) {
        String sql = "insert into writer (firstName, lastName, post_id) values (?, ?, ?)";
        String result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getPost_id());
            result = preparedStatement.executeUpdate() > 0 ?  "Writer saved successfully!" : "Failed to insert record.";
        }
        return result;
    }

    @SneakyThrows
    @Override
    public String removeById(Integer integer) {
        String sql = "delete from writer where id = ?";
        String result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);
            result = preparedStatement.executeUpdate() > 0 ? "writer remove successfully!" : "Failed to remove writer.";
        }
        return result;
    }

    @SneakyThrows
    private Writer mapToResultSetWriter(ResultSet resultSet) {
        Writer writer = new Writer();
        writer.setId(resultSet.getInt("id"));
        writer.setFirstName(resultSet.getString("firstName"));
        writer.setLastName(resultSet.getString("lastName"));
        writer.setPost_id(resultSet.getInt("post_id"));
        return writer;
    }
//    INSERT INTO Writer (firstName, lastName, post_id) VALUES ('Max', 'Korolev', 1);


}

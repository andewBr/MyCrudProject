package org.example.repository;


import lombok.SneakyThrows;
import org.example.config.DatabaseManager;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Writer;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    @SneakyThrows
    @Override
    public Post findById(Integer integer) {

        String sql = "select * from post where id = ?";
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return mapToResultSetWriter(resultSet);
            }

        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<Post> findAll() {
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "select * from post";
        try (Statement statement = DatabaseManager.statement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                posts.add(mapToResultSetWriter(resultSet));
            }
        }
        return posts;
    }

    @SneakyThrows
    @Override
    public String insert(Post entity) {
        String sql = "insert into Post (content, created, updated, label_id) values (?, ?, ?, ?);";
        String result;

        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreated()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getUpdated()));
            preparedStatement.setInt(4, entity.getLabels());

            result = preparedStatement.executeUpdate() > 0 ? "Record inserted successfully!" : "Failed to insert record.";
        }
        return result;
    }

    @SneakyThrows
    @Override
    public String removeById(Integer integer) {
        String sql = "DELETE FROM post WHERE id = ?";
        String result;
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);
            result = preparedStatement.executeUpdate() > 0 ? "Post remove successfully!" : "Failed to remove Post.";
        }
        return result;
    }

    @SneakyThrows
    private Post mapToResultSetWriter(ResultSet resultSet) {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        post.setContent(resultSet.getString("content"));
        post.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        post.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
        post.setLabels(resultSet.getInt("label_id"));
        return post;
    }
}

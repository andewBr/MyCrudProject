package org.example.repository;


import lombok.SneakyThrows;
import org.example.config.DatabaseManager;
import org.example.model.Post;

import java.sql.*;
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
    public int insert(Post entity) {
        String sql = "insert into Post (content, created, updated, label_id) values (?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreated()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getUpdated()));
            preparedStatement.setInt(4, entity.getLabels());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting post failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting post failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public String removeById(Integer postIdToRemove) {
        deleteAssociations(postIdToRemove);
        return deletePost(postIdToRemove);
    }

    @SneakyThrows
    public int insertPost(Post entity) {
        String sql = "insert into Post (content, created, updated) values (?, ?, ?);";

        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            int affectedRows = executeUpdatePostWithEntity(preparedStatement, entity);

            if (affectedRows == 0) {
                throw new SQLException("Inserting post failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting post failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public int insert2(Post entity, int labelId) {
            int postId = insertPost(entity);
            System.out.println("Post inserted with ID: " + postId);
            insertPostLabel(postId, labelId);
            System.out.println("Post associated with Label ID: " + labelId);
        return postId;
    }

    @SneakyThrows
    private void deleteAssociations(int postId) {
        String deleteAssociationsSql = "DELETE FROM Post_Label WHERE post_id = ?";
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(deleteAssociationsSql)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    private String deletePost(int postId) {
        String result;
        String deletePostSql = "DELETE FROM Post WHERE id = ?";
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(deletePostSql)) {
            preparedStatement.setInt(1, postId);
            result = preparedStatement.executeUpdate() > 0 ? "Post remove successfully!" : "Failed to remove Post.";
        }
        return result;
    }

    @SneakyThrows
    private void insertPostLabel(int postId, int labelId) {
        String insertSql = "INSERT INTO Post_Label (post_id, label_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(insertSql)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, labelId);

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    private int executeUpdatePostWithEntity(PreparedStatement preparedStatement, Post entity) {
        preparedStatement.setString(1, entity.getContent());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getCreated()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getUpdated()));
        return preparedStatement.executeUpdate();
    }

    @SneakyThrows
    private Post mapToResultSetWriter(ResultSet resultSet) {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        post.setContent(resultSet.getString("content"));
        post.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        post.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
        return post;
    }
}

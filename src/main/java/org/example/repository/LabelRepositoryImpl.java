package org.example.repository;

import lombok.SneakyThrows;
import org.example.model.Label;
import org.example.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LabelRepositoryImpl implements LabelRepository {

    private final Connection connection;

    public LabelRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    @Override
    public Label findById(Integer integer) {
        String sql = "select * from label where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return mapToResultSetLabel(resultSet);
            }
        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<Label> findAll() {
        String sql = "select * from label";
        List<Label> labels = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                labels.add(mapToResultSetLabel(resultSet));
            }
        }
        return labels;
    }

    @SneakyThrows
    @Override
    public String insert(Label entity) {
        String sql = "insert into label (name) values (?)";
        String result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            result = preparedStatement.executeUpdate() > 0 ? "Record inserted successfully!" : "Failed to insert record.";
        }
        return result;
    }

    @SneakyThrows
    @Override
    public String removeById(Integer integer) {
        String sql = "delete from label where id=?";
        String reslut;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);
            reslut = preparedStatement.executeUpdate() > 0 ? "label remove successfully!" : "Failed to remove label.";
        }
        return reslut;
    }

    @SneakyThrows
    private Label mapToResultSetLabel(ResultSet resultSet) {
        Label label = new Label();
        label.setId(resultSet.getInt("id"));
        label.setName(resultSet.getString("name"));
        return label;
    }
}

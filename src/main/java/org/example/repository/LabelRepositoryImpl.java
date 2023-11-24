package org.example.repository;

import lombok.SneakyThrows;
import org.example.config.DatabaseManager;
import org.example.model.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabelRepositoryImpl implements LabelRepository {

    @SneakyThrows
    @Override
    public Label findById(Integer integer) {
        String sql = "select * from label where id = ?";

        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
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
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                labels.add(mapToResultSetLabel(resultSet));
            }
        }
        return labels;
    }

    @SneakyThrows
    @Override
    public int insert(Label entity) {
        String sql = "insert into label (name) values (?)";
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting label failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting label failed, no ID obtained.");
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public String removeById(Integer integer) {
        String sql = "delete from label where id=?";
        String reslut;
        try (PreparedStatement preparedStatement = DatabaseManager.prepareStatement(sql)) {

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

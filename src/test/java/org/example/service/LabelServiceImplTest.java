package org.example.service;

import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LabelServiceImplTest {

    @Mock
    private LabelRepository labelRepositoryMock;

    @InjectMocks
    private LabelServiceImpl labelService;
    private Integer labelId = 1;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        labelService = new LabelServiceImpl(labelRepositoryMock);
    }

    @Test
    void findById() {
        Label expectedLabel = new Label("Lorem ipsum");
        when(labelRepositoryMock.findById(labelId)).thenReturn(expectedLabel);

        Label actualLabel = labelService.findById(labelId);

        assertEquals(actualLabel, expectedLabel);
        verify(labelRepositoryMock, times(1)).findById(labelId);
    }

    @Test
    void findAll() {
        List<Label> expectedLabel = Arrays.asList(
                new Label("Lorem ipsum"),
                new Label("dolor sit amet")
        );
        when(labelRepositoryMock.findAll()).thenReturn(expectedLabel);

        List<Label> actualLabel = labelService.findAll();

        assertEquals(actualLabel, expectedLabel);
        verify(labelRepositoryMock, times(1)).findAll();
    }

    @Test
    void insert() {
        Label expectedLabel = new Label("dolor sit amet");
        when(labelRepositoryMock.save(expectedLabel)).thenReturn("Record inserted successfully!");

        labelService.insert(expectedLabel);

        verify(labelRepositoryMock, times(1)).save(expectedLabel);
    }

    @Test
    void removeById() {
        when(labelRepositoryMock.removeById(labelId)).thenReturn("label remove successfully!");

        labelService.removeById(1);
        verify(labelRepositoryMock, times(labelId)).removeById(1);
    }
}
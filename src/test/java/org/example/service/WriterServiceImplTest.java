package org.example.service;

import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WriterServiceImplTest {

    @Mock
    private WriterRepository writerRepositoryMock;
    @InjectMocks
    private WriterServiceImpl writerService;
    private Writer expectedResult = new Writer("Chaya", "Muller", 2);
    private Integer writerId = 1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        writerService = new WriterServiceImpl(writerRepositoryMock);
    }

    @Test
    void findById() {

        when(writerRepositoryMock.findById(writerId)).thenReturn(expectedResult);
        Writer actualResult = writerService.findById(writerId);

        assertEquals(expectedResult, actualResult);
        verify(writerRepositoryMock, times(1)).findById(writerId);
    }

    @Test
    void findAll() {
        List<Writer> expectedResultList = Collections.singletonList(expectedResult);
        when(writerRepositoryMock.findAll()).thenReturn(expectedResultList);

        List<Writer> actualResult = writerService.findAll();

        assertEquals(expectedResultList, actualResult);
        verify(writerRepositoryMock, times(1)).findAll();
    }

    @Test
    void save() {
        when(writerRepositoryMock.save(expectedResult)).thenReturn("Writer saved successfully!");

        String actualResult = writerService.save(expectedResult);

        assertEquals("Writer saved successfully!", actualResult);
        verify(writerRepositoryMock, times(1)).save(expectedResult);
    }

    @Test
    void removeById() {
        when(writerRepositoryMock.removeById(writerId)).thenReturn("writer remove successfully!");
        String actualResult = writerService.removeById(writerId);

        assertEquals("writer remove successfully!", actualResult);
        verify(writerRepositoryMock, times(1)).removeById(writerId);
    }
}
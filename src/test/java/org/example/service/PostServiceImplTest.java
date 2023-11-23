package org.example.service;

import org.example.model.Post;
import org.example.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private PostServiceImpl postService;
    LocalDateTime now = LocalDateTime.now();
    private Integer postId = 1;
    private Post expectedPost = new Post("Haag Haag and Haag", now.minusDays(4), now.plusDays(3).minusHours(1), 2);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostServiceImpl(postRepositoryMock);
    }


    @Test
    void findById() {
        Post expectedPost = new Post("Haag Haag and Haag", now.minusDays(4), now.plusDays(3).minusHours(1), 2);
        when(postService.findById(postId)).thenReturn(expectedPost);

        Post actualPost = postService.findById(postId);

        assertEquals(expectedPost, actualPost);
        verify(postRepositoryMock, times(1)).findById(1);
    }

//    @Test
//    void findById() {
//        Label expectedLabel = new Label("Lorem ipsum");
//        when(labelRepositoryMock.findById(labelId)).thenReturn(expectedLabel);
//
//        Label actualLabel = labelService.findById(labelId);
//
//        assertEquals(actualLabel, expectedLabel);
//        verify(labelRepositoryMock, times(1)).findById(labelId);
//    }

    @Test
    void findAll() {
        // Arrange
        List<Post> expectedPosts = Collections.singletonList(expectedPost);
        when(postRepositoryMock.findAll()).thenReturn(expectedPosts);

        // Act
        List<Post> actualPosts = postService.findAll();

        // Assert
        assertEquals(expectedPosts, actualPosts);
        verify(postRepositoryMock, times(1)).findAll();
    }

    @Test
    void insert() {
        when(postRepositoryMock.save(expectedPost)).thenReturn("Record inserted successfully!");
        String actualPosts = postService.insert(expectedPost);

        assertEquals("Record inserted successfully!", actualPosts);
        verify(postRepositoryMock, times(1)).save(expectedPost);
    }

    @Test
    void removeById() {
        when(postRepositoryMock.removeById(postId)).thenReturn("Post remove successfully!");
        String actualResult = postService.removeById(postId);

        assertEquals("Post remove successfully!", actualResult);
        verify(postRepositoryMock, times(1)).removeById(postId);
    }
}
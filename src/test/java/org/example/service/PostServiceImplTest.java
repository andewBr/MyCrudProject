package org.example.service;

import org.example.model.Label;
import org.example.model.Post;
import org.example.repository.LabelRepository;
import org.example.repository.PostRepository;
import org.example.repository.PostRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        when(postRepositoryMock.insert(expectedPost)).thenReturn("Record inserted successfully!");
        String actualPosts = postService.insert(expectedPost);

        assertEquals("Record inserted successfully!", actualPosts);
        verify(postRepositoryMock, times(1)).insert(expectedPost);
    }

    @Test
    void removeById() {
        when(postRepositoryMock.removeById(postId)).thenReturn("Post remove successfully!");
        String actualResult = postService.removeById(postId);

        assertEquals("Post remove successfully!", actualResult);
        verify(postRepositoryMock, times(1)).removeById(postId);
    }
}

//====================== label CRUD ======================
//        label findById 1:null
//        remove row by id 1: Failed to remove label.
//        ============================================
//        find all existing labels: [Label(id=2, name=Taming a Sea Horse), Label(id=3, name=A Darkling Plain), Label(id=4, name=That Hideous Strength)]
//        ====================== post CRUD ======================
//        post findById 1: null
//        remove row by id 1: Failed to remove Post.
//        ============================================
//        find all existing posts: [Post(id=2, content=Haag, Haag and Haag, created=2023-11-19T20:15:02, updated=2023-11-12T20:15:02, labels=2), Post(id=3, content=Kirlin Inc, created=2023-11-20T16:46, updated=2023-11-13T16:46, labels=2), Post(id=4, content=Paucek LLC, created=2023-11-20T16:46, updated=2023-11-13T16:46, labels=2)]
//        ====================== Writer CRUD ======================
//        writer findById 1: null
//        remove row by id 1: Failed to remove writer.
//        ============================================
//        find all existing writers: [Writer(id=2, firstName=Chaya, lastName=Muller, post_id=2), Writer(id=3, firstName=Lysanne, lastName=Rosenbaum, post_id=2), Writer(id=4, firstName=Katrina, lastName=Ullrich, post_id=2)]

package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Post {
    private int id;
    private String content;
    private LocalDateTime  created;
    private LocalDateTime  updated;
    private int labels;

    public Post(String content, LocalDateTime created, LocalDateTime updated, int labels) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
    }
}

package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Writer {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer post_id;

    public Writer(String firstName, String lastName, Integer post_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.post_id = post_id;
    }
}

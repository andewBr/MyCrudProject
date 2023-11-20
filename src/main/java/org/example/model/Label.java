package org.example.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Label {
    private int id;
    private String name;

    public Label(String name) {
        this.name = name;
    }
}

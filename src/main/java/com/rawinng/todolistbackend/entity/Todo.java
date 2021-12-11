package com.rawinng.todolistbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    private String id;
    private String title;
    private Boolean completed;
}

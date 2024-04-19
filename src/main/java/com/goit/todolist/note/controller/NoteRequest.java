package com.goit.todolist.note.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * NoteRequest object.
 */
@Data
public class NoteRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

}

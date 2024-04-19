package com.goit.todolist.note.controller;

import lombok.Data;

/**
 * NoteResponse object.
 */
@Data
public class NoteResponse {

    private Long id;
    private String title;
    private String content;

}

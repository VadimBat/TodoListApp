package com.goit.todolist.note.service;

import lombok.Data;

/**
 * NoteDto object.
 */
@Data
public class NoteDto {

    private Long id;
    private String title;
    private String content;

}

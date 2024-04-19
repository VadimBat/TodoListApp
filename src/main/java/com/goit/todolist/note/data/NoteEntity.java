package com.goit.todolist.note.data;

import lombok.Data;

/**
 * NoteEntity object.
 */
@Data
public class NoteEntity {

    private Long id;
    private String title;
    private String content;

}

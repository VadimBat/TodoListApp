package com.goit.todolist.note.data;

import jakarta.persistence.*;
import lombok.Data;

/**
 * NoteEntity object.
 */
@Data
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

}

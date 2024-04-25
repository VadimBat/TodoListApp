package com.goit.todolist.note.data;

import com.goit.todolist.note.controller.config.NoteNotFoundException;

import java.util.List;

/**
 * Interface describing notes entity objects CRUD operations.
 */
public interface NoteService {
    List<NoteEntity> listAll();

    NoteEntity add(NoteEntity note);

    void deleteById(long id) throws NoteNotFoundException;

    void update(NoteEntity note) throws NoteNotFoundException;

    NoteEntity getById(long id) throws NoteNotFoundException;
}

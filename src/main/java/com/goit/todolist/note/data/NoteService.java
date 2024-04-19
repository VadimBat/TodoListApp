package com.goit.todolist.note.data;

import java.util.List;

/**
 * Interface describing notes entity objects CRUD operations.
 */
public interface NoteService {
    List<NoteEntity> listAll();

    NoteEntity add(NoteEntity note);

    void deleteById(long id);

    void update(NoteEntity note);

    NoteEntity getById(long id);
}

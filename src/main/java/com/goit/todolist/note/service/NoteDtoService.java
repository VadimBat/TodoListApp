package com.goit.todolist.note.service;

import com.goit.todolist.note.controller.config.NoteNotFoundException;

import java.util.List;

/**
 * Interface describing notes DTO objects CRUD operations.
 */
public interface NoteDtoService {
    List<NoteDto> listAll();

    NoteDto add(NoteDto note);

    void deleteById(long id) throws NoteNotFoundException;

    void update(NoteDto note) throws NoteNotFoundException;

    NoteDto getById(long id) throws NoteNotFoundException;
}

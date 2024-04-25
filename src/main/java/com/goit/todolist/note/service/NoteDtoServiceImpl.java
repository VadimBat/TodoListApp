package com.goit.todolist.note.service;

import com.goit.todolist.note.controller.config.NoteNotFoundException;
import com.goit.todolist.note.data.NoteEntity;
import com.goit.todolist.note.data.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * DAO service implements CRUD-operations for note dto.
 */
@Service
public class NoteDtoServiceImpl implements NoteDtoService {

    @Autowired
    private NoteServiceImpl noteService;
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        return noteMapper.toNoteDtos(noteService.listAll());
    }

    @Override
    public NoteDto add(NoteDto note) {
        NoteEntity entity = noteMapper.toNoteEntity(note);
        return noteMapper.toNoteDto(noteService.add(entity));
    }

    @Override
    public void deleteById(long id) throws NoteNotFoundException {
        getById(id);
        noteService.deleteById(id);
    }

    @Override
    public void update(NoteDto note) throws NoteNotFoundException {
        if (Objects.isNull(note.getId())) {
            throw new NoteNotFoundException();
        }
        getById(note.getId());
        noteService.update(noteMapper.toNoteEntity(note));
    }

    @Override
    public NoteDto getById(long id) throws NoteNotFoundException {
        NoteEntity optionalNote = noteService.getById(id);
        if (optionalNote != null) {
            return noteMapper.toNoteDto(optionalNote);
        } else {
            throw new NoteNotFoundException(id);
        }
    }
}

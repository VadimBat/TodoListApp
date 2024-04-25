package com.goit.todolist.note.data;

import com.goit.todolist.note.controller.config.NoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DAO service implements CRUD-operations for note entity.
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public List<NoteEntity> listAll() {
        log.info("Getting all notes...");
        return noteRepository.findAll();
    }

    @Override
    public NoteEntity add(NoteEntity note) {
        note.setId(Math.abs(new Random().nextLong()));
        noteRepository.save(note);
        log.info("Note added!");
        return note;
    }

    @Override
    public void deleteById(long id) throws NoteNotFoundException {
        Optional<NoteEntity> noteToRemove =
                noteRepository.findAll().stream()
                        .filter(toRemove -> toRemove.getId().equals(id))
                        .findFirst();
        if (noteToRemove.isPresent()) {
            noteRepository.delete(noteToRemove.get());
            log.info("Note deleted!");
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    @Override
    public void update(NoteEntity note) throws NoteNotFoundException {
        if (Objects.isNull(note.getId())) {
            throw new NoteNotFoundException();
        }
        getById(note.getId());
        note.setTitle(note.getTitle());
        note.setContent(note.getContent());
        noteRepository.save(note);
    }

    @Override
    public NoteEntity getById(long id) throws NoteNotFoundException {
        Optional<NoteEntity> note = noteRepository.findById(id);
        if (!note.isPresent()) {
            throw new NoteNotFoundException(id);
        }
        log.info("Note got!");
        return note.get();
    }
}

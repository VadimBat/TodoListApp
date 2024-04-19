package com.goit.todolist.note.data;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DAO service implements CRUD-operations for note entity.
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public List<NoteEntity> listAll() {
        log.info("Getting all notes...");
        return Repository.NOTES;
    }

    @Override
    public NoteEntity add(NoteEntity note) {
        note.setId(Math.abs(new Random().nextLong()));
        Repository.NOTES.add(note);
        log.info("Note added!");
        return note;
    }

    @Override
    public void deleteById(long id) {
        Optional<NoteEntity> noteToRemove =
                Repository.NOTES.stream()
                        .filter(toRemove -> toRemove.getId().equals(id))
                        .findFirst();
        if (noteToRemove.isPresent()) {
            Repository.NOTES.remove(noteToRemove.get());
            log.info("Note deleted!");
        } else {
            throw new IllegalArgumentException(
                    "Note with id = " + id + " doesn't exist! Please enter other id"
            );
        }
    }

    @Override
    public void update(NoteEntity note) {
        for (NoteEntity noteToUpdate : Repository.NOTES) {
            if (Objects.equals(noteToUpdate.getId(), note.getId())) {
                noteToUpdate.setTitle(note.getTitle());
                noteToUpdate.setContent(note.getContent());
                log.info("Note updated!");
                return;
            }
        }
        throw new IllegalArgumentException(
                "Note with id = " + note.getId() + " doesn't exist! Please enter other id"
        );
    }

    @Override
    public NoteEntity getById(long id) {
        Optional<NoteEntity> note = Repository.NOTES.stream()
                .filter(getNote -> getNote.getId().equals(id))
                .findFirst();
        if (!note.isPresent()) {
            throw new IllegalArgumentException(
                    "Note with id = " + id + " doesn't exist! Please enter other id"
            );
        }
        log.info("Note got!");
        return note.get();
    }
}

package com.goit.todolist.note.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;


class NoteServiceImplTest {

    private NoteServiceImpl noteService = new NoteServiceImpl();
    private NoteEntity originalNote = new NoteEntity();

    @BeforeEach
    void beforeEach() {
        //Given
        originalNote.setId(Math.abs(new Random().nextLong()));
        originalNote.setTitle("Test title");
        originalNote.setContent("Test content");
    }

    @Test
    void listAllTest() {
        //When
        NoteEntity expectedNote = noteService.add(originalNote);
        List<NoteEntity> expected = Collections.singletonList(expectedNote);
        List<NoteEntity> actual = noteService.listAll();

        //Then
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    void addTest() {
        //When
        NoteEntity expectedNote = noteService.add(originalNote);

        //Then
        Assertions.assertEquals(expectedNote.getTitle(), originalNote.getTitle());
        Assertions.assertEquals(expectedNote.getContent(), originalNote.getContent());
    }

    @Test
    void deleteByIdTest() {
        //When
        NoteEntity expectedNote = noteService.add(originalNote);
        long savedId = expectedNote.getId();
        noteService.deleteById(savedId);

        //Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> noteService.getById(savedId));
    }

    @Test
    void updateTest() {
        //When
        NoteEntity savedNote = noteService.add(originalNote);
        long savedId = savedNote.getId();

        NoteEntity updatedNote = new NoteEntity();
        updatedNote.setId(savedId);
        updatedNote.setTitle("New Title");
        updatedNote.setContent("New Content");

        noteService.update(updatedNote);

        NoteEntity actualNote = noteService.getById(updatedNote.getId());

        //Then
        Assertions.assertEquals(savedNote.getId(), actualNote.getId());
        Assertions.assertEquals("New Title", actualNote.getTitle());
        Assertions.assertEquals("New Content", actualNote.getContent());
    }

    @Test
    void getById() {
        //When
        NoteEntity expectedNote = noteService.add(originalNote);
        long expectedId = originalNote.getId();

        //Then
        NoteEntity actualNote = noteService.getById(expectedId);
        Long actualId = actualNote.getId();
        Assertions.assertEquals(actualId, expectedId);
    }
}
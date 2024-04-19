package com.goit.todolist.note.controller.config;

/**
 * NoteNotFoundException describes exceptions while note is not found.
 */
public class NoteNotFoundException extends Exception {

    private static final String NOTE_NOT_FOUND_EXCEPTION_MESSAGE = "Note with id = %s is not found!";
    private static final String CAN_NOT_UPDATE_NOTE_WITHOUT_ID_EXCEPTION_MESSAGE = "Can not found note without id!";
    public NoteNotFoundException() {
        super(CAN_NOT_UPDATE_NOTE_WITHOUT_ID_EXCEPTION_MESSAGE);
    }
    public NoteNotFoundException(long noteId) {
        super(String.format(NOTE_NOT_FOUND_EXCEPTION_MESSAGE, noteId));
    }

}

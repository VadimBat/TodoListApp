package com.goit.todolist.note.controller.controller;

import com.goit.todolist.note.service.NoteDto;
import com.goit.todolist.note.service.NoteDtoServiceImpl;
import com.goit.todolist.note.service.NoteMapper;
import com.goit.todolist.note.controller.config.NoteNotFoundException;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * NoteController makes all responses on all requests.
 */
@Validated
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteDtoServiceImpl noteService;
    @Autowired
    private NoteMapper noteMapper;

    @PostMapping("/create")
    public ModelAndView createNote(@NotEmpty @RequestParam(value = "title") String title,
                                   @NotEmpty @RequestParam(value = "content") String content) {
        NoteDto note = new NoteDto();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
        return getNotesList();
    }

    @GetMapping("/list")
    public ModelAndView getNotesList() {
        ModelAndView result = new ModelAndView("note/allNotes");
        result.addObject("notes", noteMapper.toNoteResponses(noteService.listAll()));
        return result;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNoteById(@RequestParam(value = "id") long id) {

        try {
            noteService.deleteById(id);
        } catch (NoteNotFoundException e) {
            e.printStackTrace();
        }
        return getNotesList();
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@RequestParam(value = "id") long id) {
        ModelAndView result = new ModelAndView("note/editNoteById");
        try {
            result.addObject("note", noteMapper.toNoteResponse(noteService.getById(id)));
        } catch (NoteNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public ModelAndView editNote(@RequestParam(value = "id") long id,
                                 @NotEmpty @RequestParam(value = "title") String title,
                                 @NotEmpty @RequestParam(value = "content") String content) {
        NoteDto note = new NoteDto();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        try {
            noteService.update(note);
        } catch (NoteNotFoundException e) {
            e.printStackTrace();
        }
        return getNotesList();
    }

    @GetMapping("/start")
    public ModelAndView start() {
        return new ModelAndView("note/start");
    }

    @GetMapping("/create")
    public ModelAndView createNoteFrom() {
        return new ModelAndView("note/createNote");
    }

    @GetMapping("/delete")
    private ModelAndView deleteNoteForm() {
        return new ModelAndView("note/deleteNote");
    }

    @GetMapping("/update")
    private ModelAndView updateNoteForm() {
        return new ModelAndView("note/editNote");
    }

}

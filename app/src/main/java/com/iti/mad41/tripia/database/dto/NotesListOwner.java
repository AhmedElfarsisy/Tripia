package com.iti.mad41.tripia.database.dto;

import java.util.List;

public class NotesListOwner {
    private List<Note> notesList;

    public NotesListOwner() {
    }

    public NotesListOwner(List<Note> notesList) {
        this.notesList = notesList;
    }

    public List<Note> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Note> notesList) {
        this.notesList = notesList;
    }
}

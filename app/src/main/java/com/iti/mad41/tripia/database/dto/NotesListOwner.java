package com.iti.mad41.tripia.database.dto;

import java.util.List;

public class NotesListOwner {
    private List<TripNotes> notesList;

    public NotesListOwner() {
    }

    public NotesListOwner(List<TripNotes> notesList) {
        this.notesList = notesList;
    }

    public List<TripNotes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<TripNotes> notesList) {
        this.notesList = notesList;
    }
}

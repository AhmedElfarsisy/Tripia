package com.iti.mad41.tripia.database.dto;

public class TripNotes {
    private String noteBody;

    public TripNotes() {
    }

    public TripNotes(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

}

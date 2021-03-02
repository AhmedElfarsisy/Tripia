package com.iti.mad41.tripia.database.dto;

public class Note {
    private String noteBody;

    public Note() {
    }

    public Note(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

}

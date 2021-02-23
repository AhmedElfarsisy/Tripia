package com.iti.mad41.tripia.model;

public class Note {
    private int notesId;
    private String noteBody;
    public Note(int notesId, String noteBody) {
        this.notesId = notesId;
        this.noteBody = noteBody;
    }
    public int getNotesId() {
        return notesId;
    }
    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }
    public String getNoteBody() {
        return noteBody;
    }
    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
}
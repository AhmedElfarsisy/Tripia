package com.iti.mad41.tripia.database.dto;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class NoteConverter {
    @TypeConverter
    public String convertNotesToString(NotesListOwner notesList) {
        return new Gson().toJson(notesList);
    }
    @TypeConverter
    public NotesListOwner convertStringToNotesList(String  notesString) {
        return new Gson().fromJson(notesString, NotesListOwner.class);
    }

}

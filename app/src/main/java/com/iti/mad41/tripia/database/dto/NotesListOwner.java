package com.iti.mad41.tripia.database.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NotesListOwner implements Parcelable {
    private List<Note> notesList;

    public NotesListOwner() {
    }

    public NotesListOwner(List<Note> notesList) {
        this.notesList = notesList;
    }

    protected NotesListOwner(Parcel in) {
        notesList = in.createTypedArrayList(Note.CREATOR);
    }

    public static final Creator<NotesListOwner> CREATOR = new Creator<NotesListOwner>() {
        @Override
        public NotesListOwner createFromParcel(Parcel in) {
            return new NotesListOwner(in);
        }

        @Override
        public NotesListOwner[] newArray(int size) {
            return new NotesListOwner[size];
        }
    };

    public List<Note> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Note> notesList) {
        this.notesList = notesList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(notesList);
    }
}

package com.iti.mad41.tripia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Notes implements Parcelable {

    private List<Note> noteList ;
    public Notes(List<Note> noteList) {
        this.noteList=noteList;
    }

    protected Notes(Parcel in) {
        noteList = in.createTypedArrayList(Note.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(noteList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}

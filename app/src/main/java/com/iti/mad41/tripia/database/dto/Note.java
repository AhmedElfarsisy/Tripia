package com.iti.mad41.tripia.database.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String noteBody;

    public Note() {
    }

    public Note(String noteBody) {
        this.noteBody = noteBody;
    }

    protected Note(Parcel in) {
        noteBody = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(noteBody);
    }
}

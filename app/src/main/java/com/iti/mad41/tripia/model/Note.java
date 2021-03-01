package com.iti.mad41.tripia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Note  implements Parcelable {
    private int notesId;
    private String noteBody;
    public Note(int notesId, String noteBody) {
        this.notesId = notesId;
        this.noteBody = noteBody;
    }

    protected Note(Parcel in) {
        notesId = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(notesId);
        dest.writeString(noteBody);
    }
}
package com.iti.mad41.tripia.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.mad41.tripia.database.dto.Note;
import com.iti.mad41.tripia.databinding.NoteRowBinding;

import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesHolder>{
    private static final String TAG = "NotesAdapter";
    private List<Note> noteList;
    private Context context;
    public NotesAdapter(Context _context, List<Note> noteList) {
        this.context = _context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NoteRowBinding noteRowBinding = NoteRowBinding.inflate(layoutInflater, parent, false);
        return new NotesHolder(noteRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        Note note = noteList.get(position);
        holder.bind(note);
        holder.noteRowBinding.imageView2.setOnClickListener(v -> {
            noteList.remove(noteList.get(position));
            notifyDataSetChanged();
        });

    }
    @Override
    public void onViewDetachedFromWindow(@NonNull NotesHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }
}

class NotesHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "NotesHolder";
    NoteRowBinding noteRowBinding;

    public NotesHolder(@NonNull NoteRowBinding noteRowBinding) {
        super(noteRowBinding.getRoot());
        this.noteRowBinding = noteRowBinding;
    }

    public void bind(Note note){
        Log.i(TAG, "bind: ");
        noteRowBinding.setNote(note);
        noteRowBinding.executePendingBindings();
    }

    public void unbind(){
        if(noteRowBinding !=  null){
            noteRowBinding.unbind();
        }
    }

}

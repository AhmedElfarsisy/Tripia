package com.iti.mad41.tripia.ui.fragment.notes;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapters.NotesAdapter;
import com.iti.mad41.tripia.databinding.NotesFragmentBinding;
import com.iti.mad41.tripia.model.Note;
import com.iti.mad41.tripia.model.Notes;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.ui.fragment.form.FormFragment;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    private static final String TAG = "NotesFragment";
    NotesFragmentBinding binding;
    private NotesViewModel mViewModel;
    List<Note> noteList = new ArrayList<>();
    NotesAdapter notesAdapter;
    Trip trip;
    FirebaseRepo firebaseRepo;
    boolean isTripObject = false;
    Notes notes;
    boolean isNotesObject = false;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            trip = getArguments().getParcelable("Trip");
            notes = getArguments().getParcelable("Notes");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRepo = new FirebaseRepo(getActivity());
        mViewModel = new ViewModelProvider(this, new NotesViewModelFactory(firebaseRepo)).get(NotesViewModel.class);
        binding.setNoteViewModel(mViewModel);
        binding.setLifecycleOwner(this);
/*        if (trip.getNoteList()!=null) {
            noteList=trip.getNoteList();
            addNotesToAdapter(noteList);
            isTripObject = true;
        }*/
        if (trip != null) {
            isTripObject = true;
        }
        if (notes != null) {
            Log.i(TAG, "onActivityCreated: on if and note not null" + notes.getNoteList().get(0).getNoteBody());
            noteList =notes.getNoteList();
            addNotesToAdapter(notes.getNoteList());
            isNotesObject = true;
        } else
            Log.i(TAG, "onActivityCreated: on else and not found Notes");

        binding.toolbar.setNavigationOnClickListener(v -> {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new FormFragment())
                    .commit();
        });
        mViewModel.isClickSkip.observe(getViewLifecycleOwner(), isClickSkip -> {
            if (isClickSkip) {
                if (isTripObject) {
                    Log.i(TAG, "onActivityCreated: *********** if trip object in Skip");
                    mViewModel.setTripToUpdateTripOnFirebase(trip);
                }
                mViewModel.setTripToUploadOnFirebase(trip);
                Toast.makeText(getActivity(), "Trip saved without notes", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        mViewModel.isClickDone.observe(getViewLifecycleOwner(), isClickDone -> {
            if (isClickDone) {
                //trip.setNoteList(noteList);
                /*List<Note> notes  = new ArrayList<>();
                notes = noteList;*/
                if (isTripObject && isNotesObject) {
                    mViewModel.setTripToUpdateTripOnFirebase(trip);
                    notes.setNoteList(noteList);
                    mViewModel.setTripToUpdateNotesOnFirebase(notes);
                    Log.i(TAG, "onActivityCreated: *********** if trip object on Done");
                } else {
                    mViewModel.setTripToUploadOnFirebase(trip);
                }
                Toast.makeText(getActivity(), "Trip saved ", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        mViewModel.addTitle.observe(getViewLifecycleOwner(), isAddTitle -> {
            if (isAddTitle) {
                noteList.add(new Note(1, binding.editTextAddNote.getText().toString()));
                addNotesToAdapter(noteList);
                binding.editTextAddNote.setText("");
            }
        });
    }

    private void addNotesToAdapter(List<Note> noteList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerViewNotes.setLayoutManager(layoutManager);
        notesAdapter = new NotesAdapter(getActivity(), noteList);
        binding.recyclerViewNotes.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }
}
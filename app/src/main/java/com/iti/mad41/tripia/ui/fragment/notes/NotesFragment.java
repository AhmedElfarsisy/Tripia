package com.iti.mad41.tripia.ui.fragment.notes;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapters.NotesAdapter;
import com.iti.mad41.tripia.database.dto.Note;
import com.iti.mad41.tripia.database.dto.NotesListOwner;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.databinding.NotesFragmentBinding;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
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
    TripsDataRepository tripsDataRepository;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        trip = (Trip) getArguments().getParcelable("Trip");
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment, container, false);
        tripsDataRepository = TripsDataRepository.getINSTANCE(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRepo = new FirebaseRepo(getActivity());
        mViewModel = new ViewModelProvider(this, new NotesViewModelFactory(firebaseRepo, tripsDataRepository)).get(NotesViewModel.class);
        binding.setNoteViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        binding.toolbar.setNavigationOnClickListener(v -> {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new FormFragment())
                    .commit();
        });
        mViewModel.isClickSkip.observe(getViewLifecycleOwner(), isClickSkip -> {
            if (isClickSkip) {
                mViewModel.setTrip(trip);
                //schedule trip
                trip.schedule(getActivity());
                Toast.makeText(getActivity(), "Trip saved without notes", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        mViewModel.isClickDone.observe(getViewLifecycleOwner(), isClickDone -> {
            if (isClickDone) {

                mViewModel.setTrip(trip);
                trip.setNotesListOwner(new NotesListOwner(noteList));
                mViewModel.createLocalTrip(trip);

                mViewModel.setNote(trip.getFirebaseId(), noteList);
                //schedule trip
                trip.schedule(getActivity());
                Toast.makeText(getActivity(), "Trip saved ", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

        });

        mViewModel.operationResult.observe(getViewLifecycleOwner(), s -> {
            if (!s.isEmpty()) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.addTitle.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                noteList.add(new Note(binding.editTextAddNote.getText().toString()));
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
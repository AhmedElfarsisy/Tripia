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
import com.iti.mad41.tripia.database.dto.Note;
import com.iti.mad41.tripia.database.dto.NotesListOwner;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.databinding.NotesFragmentBinding;
import com.iti.mad41.tripia.helper.Constants;
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
    private boolean isNavigateToUpdate;
    private Trip updateTripObject;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment, container, false);
        tripsDataRepository = TripsDataRepository.getINSTANCE(getActivity());
        //TODO: update it's name to work for both
        trip = getArguments().getParcelable(Constants.UPDATE_TRIP);
        isNavigateToUpdate = getArguments().getBoolean(Constants.IS_NAVIGATE_TO_UPDATE);
        if (isNavigateToUpdate) {
            trip = getArguments().getParcelable(Constants.UPDATE_TRIP);
            Log.i(TAG, "onCreateView: " + trip.toString());
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRepo = new FirebaseRepo(getActivity());
        mViewModel = new ViewModelProvider(this, new NotesViewModelFactory(firebaseRepo, tripsDataRepository)).get(NotesViewModel.class);
        binding.setNoteViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        if (trip.getNotesListOwner() != null) {
            noteList = trip.getNotesListOwner().getNotesList();
            addNotesToAdapter(noteList);
        }

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

                trip.setNotesListOwner(new NotesListOwner(noteList));
                if(isNavigateToUpdate){
                    mViewModel.updateLocalTrip(trip);
                } else {
                    mViewModel.createLocalTrip(trip);
                }

                mViewModel.setTrip(trip);
                mViewModel.setNote(trip.getFirebaseId(), noteList);
                //schedule trip
                if (trip.getDateTime() > System.currentTimeMillis())
                    trip.schedule(getActivity());

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
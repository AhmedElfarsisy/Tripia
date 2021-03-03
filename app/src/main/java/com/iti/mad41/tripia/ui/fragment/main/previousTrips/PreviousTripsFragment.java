package com.iti.mad41.tripia.ui.fragment.main.previousTrips;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapters.PreviousTripsAdapter;
import com.iti.mad41.tripia.adapters.onPreviousTripsClickCallback;
import com.iti.mad41.tripia.adapters.onUpcomingTripsClickCallback;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.databinding.FragmentPreviousTripBinding;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.dialog.ConfirmDialog;
import com.iti.mad41.tripia.ui.dialog.onConfirmDialogClickCallback;
import com.iti.mad41.tripia.ui.fragment.main.upcomingTrips.UpcomingTripsFragment;

public class PreviousTripsFragment extends Fragment {
    private static final String TAG = UpcomingTripsFragment.class.getSimpleName();
    private PreviousTripsAdapter tripsAdapter;
    private RecyclerView previousRecyclerView;
    private FirebaseRepo firebaseRepo;
    private TripsDataRepository tripsDataRepository;
    private PreviousTripsViewModel mViewModel;
    private FragmentPreviousTripBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_previous_trip, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRepo = new FirebaseRepo();
        tripsDataRepository = TripsDataRepository.getINSTANCE(getActivity());
        mViewModel = new ViewModelProvider(this, new PreviousTripsViewModelFactory(firebaseRepo, tripsDataRepository)).get(PreviousTripsViewModel.class);
        previousRecyclerView = binding.previousRecyclerView;
        previousRecyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        previousRecyclerView.setLayoutManager(linearLayoutManager);
        tripsAdapter = new PreviousTripsAdapter();
        tripsAdapter.setPreviousTripClickCallback(new onPreviousTripsClickCallback() {
            @Override
            public void onDeleteClick(Trip trip) {
                ConfirmDialog confirmDialog = new ConfirmDialog("Are you sure you want to delete this trip?", "Delete", "Cancel");
                confirmDialog.setConfirmDialogClickCallback(new onConfirmDialogClickCallback() {
                    @Override
                    public void onPositiveButtonClick(DialogInterface dialog, int id) {
                        mViewModel.deleteTrip(trip);
                    }

                    @Override
                    public void onNegativeButtonClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                confirmDialog.show(getFragmentManager(), "Delete Trip");
            }
        });
        previousRecyclerView.setAdapter(tripsAdapter);

        mViewModel.livePreviousTripsList.observe(getViewLifecycleOwner(), trips -> {
            tripsAdapter.setData(trips);
            tripsAdapter.notifyDataSetChanged();
        });
    }
}
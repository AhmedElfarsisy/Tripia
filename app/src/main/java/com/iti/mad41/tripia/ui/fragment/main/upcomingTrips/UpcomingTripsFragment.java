package com.iti.mad41.tripia.ui.fragment.main.upcomingTrips;

import android.content.Intent;
import android.net.Uri;
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

import com.facebook.CallbackManager;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapters.UpcomingTripsAdapter;
import com.iti.mad41.tripia.adapters.onUpcomingTripsClickCallback;
import com.iti.mad41.tripia.databinding.FragmentUpcomingTripBinding;
import com.iti.mad41.tripia.databinding.RegisterFragmentBinding;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.model.TripsRepo;
import com.iti.mad41.tripia.repository.facebook.FacebookRepo;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleRepo;
import com.iti.mad41.tripia.ui.fragment.notes.NotesViewModel;
import com.iti.mad41.tripia.ui.fragment.register.RegisterViewModel;
import com.iti.mad41.tripia.ui.fragment.register.RegisterViewModelFactory;

public class UpcomingTripsFragment extends Fragment {
    private static final String TAG = UpcomingTripsFragment.class.getSimpleName();
    private RecyclerView upcomingRecyclerView;
    private TripsRepo tripsRepo;
    private UpcomingTripsAdapter tripsAdapter;
    private FirebaseRepo firebaseRepo;
    private UpcomingTripsViewModel mViewModel;
    private FragmentUpcomingTripBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_trip, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRepo = new FirebaseRepo();
        mViewModel = new ViewModelProvider(this, new UpcomingTripsViewModelFactory(firebaseRepo)).get(UpcomingTripsViewModel.class);
        upcomingRecyclerView = binding.upcomingRecyclerView;
        tripsRepo = new TripsRepo();
        upcomingRecyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        upcomingRecyclerView.setLayoutManager(linearLayoutManager);
        tripsAdapter = new UpcomingTripsAdapter();
        tripsAdapter.setOnUpcomingTripClickCallback(new onUpcomingTripsClickCallback() {
            @Override
            public void onStartClick(Trip trip) {
                displayTrack(trip.getStartAddress(), trip.getDestinationAddress());
            }
        });
        upcomingRecyclerView.setAdapter(tripsAdapter);

        mViewModel.liveUpcomingTripsList.observe(getViewLifecycleOwner(), trips -> {
            tripsAdapter.setData(trips);
            tripsAdapter.notifyDataSetChanged();
        });
    }

    private void displayTrack(String Start, String destination){
        Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + Start + "&destination=" + destination + "&travelmode=car");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
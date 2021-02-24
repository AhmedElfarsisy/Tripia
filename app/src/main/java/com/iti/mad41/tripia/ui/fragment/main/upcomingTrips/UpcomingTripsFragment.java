package com.iti.mad41.tripia.ui.fragment.main.upcomingTrips;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapters.UpcomingTripsAdapter;
import com.iti.mad41.tripia.adapters.onUpcomingTripsClickCallback;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.model.TripsRepo;

public class UpcomingTripsFragment extends Fragment {
    private static final String TAG = UpcomingTripsFragment.class.getSimpleName();
    private RecyclerView upcomingRecyclerView;
    private TripsRepo tripsRepo;
    private UpcomingTripsAdapter tripsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_trip, container, false);
        upcomingRecyclerView = view.findViewById(R.id.upcomingRecyclerView);
        tripsRepo = new TripsRepo();
        upcomingRecyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        upcomingRecyclerView.setLayoutManager(linearLayoutManager);
        tripsAdapter = new UpcomingTripsAdapter(getActivity(), tripsRepo.getTripsList());
        tripsAdapter.setOnUpcomingTripClickCallback(new onUpcomingTripsClickCallback() {
            @Override
            public void onStartClick(Trip trip) {
                displayTrack(trip.getStartAddress(), trip.getDestinationAddress());
            }
        });
        upcomingRecyclerView.setAdapter(tripsAdapter);
        return view;
    }

    private void displayTrack(String Start, String destination){
        Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + Start + "&destination=" + destination + "&travelmode=car");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
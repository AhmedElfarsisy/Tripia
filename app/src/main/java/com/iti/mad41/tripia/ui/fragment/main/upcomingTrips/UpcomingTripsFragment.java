package com.iti.mad41.tripia.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapter.UpcomingTripsAdapter;
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
        upcomingRecyclerView.setAdapter(tripsAdapter);
        return view;
    }
}
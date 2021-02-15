package com.iti.mad41.tripia.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.adapter.PreviousTripsAdapter;
import com.iti.mad41.tripia.model.TripsRepo;

public class PreviousTripsFragment extends Fragment {
    private static final String TAG = UpcomingTripsFragment.class.getSimpleName();
    private RecyclerView previousRecyclerView;
    private TripsRepo tripsRepo;
    private PreviousTripsAdapter tripsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_trip, container, false);
        previousRecyclerView = view.findViewById(R.id.previousRecyclerView);
        tripsRepo = new TripsRepo();
        previousRecyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        previousRecyclerView.setLayoutManager(linearLayoutManager);
        tripsAdapter = new PreviousTripsAdapter(getActivity(), tripsRepo.getTripsList());
        previousRecyclerView.setAdapter(tripsAdapter);
        return view;
    }
}
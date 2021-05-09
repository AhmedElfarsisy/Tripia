package com.iti.mad41.tripia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.databinding.PreviousTripCardBinding;
import com.iti.mad41.tripia.databinding.UpcomingTripCardBinding;

import java.util.List;

public class PreviousTripsAdapter extends RecyclerView.Adapter<PreviousTripHolder> {
    private List<Trip> tripsList;
    private onPreviousTripsClickCallback onPreviousTripsClickCallback;

    public PreviousTripsAdapter() {}

    public void setData(List<Trip> tripsList){
        this.tripsList = tripsList;
    }

    public void setPreviousTripClickCallback(onPreviousTripsClickCallback onPreviousTripsClickCallback){
        this.onPreviousTripsClickCallback = onPreviousTripsClickCallback;
    }

    @NonNull
    @Override
    public PreviousTripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PreviousTripCardBinding previousTripCardBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.previous_trip_card, parent, false);
        return new PreviousTripHolder(previousTripCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousTripHolder holder, int position) {
        Trip trip = tripsList.get(position);
        holder.bind(trip);

        holder.previousTripCardBinding.deleteIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { onPreviousTripsClickCallback.onDeleteClick(trip); }
        });
    }
    @Override
    public void onViewDetachedFromWindow(@NonNull PreviousTripHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return tripsList != null? tripsList.size() : 0;
    }
}

class PreviousTripHolder extends RecyclerView.ViewHolder {
    PreviousTripCardBinding previousTripCardBinding;

    public PreviousTripHolder(@NonNull PreviousTripCardBinding previousTripCardBinding) {
        super(previousTripCardBinding.getRoot());
        this.previousTripCardBinding = previousTripCardBinding;
    }

    public void bind(Trip trip){
        previousTripCardBinding.setPreviousTrips(trip);
        previousTripCardBinding.executePendingBindings();
    }

    public void unbind(){
        if(previousTripCardBinding !=  null){
            previousTripCardBinding.unbind();
        }
    }

}

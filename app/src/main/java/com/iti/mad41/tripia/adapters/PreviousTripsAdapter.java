package com.iti.mad41.tripia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.mad41.tripia.databinding.PreviousTripCardBinding;
import com.iti.mad41.tripia.model.Trip;

import java.util.List;

public class PreviousTripsAdapter extends RecyclerView.Adapter<PreviousTripHolder> {
    private List<Trip> tripsList;
    private Context context;

    public PreviousTripsAdapter(Context _context, List<Trip> tripsList) {
            this.context = _context;
            this.tripsList = tripsList;
    }

    @NonNull
    @Override
    public PreviousTripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PreviousTripCardBinding previousTripCardBinding = PreviousTripCardBinding.inflate(layoutInflater, parent, false);
        return new PreviousTripHolder(previousTripCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousTripHolder holder, int position) {
        Trip trip = tripsList.get(position);
        holder.bind(trip);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PreviousTripHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
            return tripsList.size();
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

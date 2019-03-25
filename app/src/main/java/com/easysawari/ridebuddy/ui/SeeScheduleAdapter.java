package com.easysawari.ridebuddy.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easysawari.ridebuddy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SeeScheduleAdapter extends RecyclerView.Adapter<SeeScheduleAdapter.AllTripHolder> {
    public SeeScheduleAdapter(Context sContext, List<OwnerTripDetail> sData) {
        this.sContext = sContext;
        this.sData = sData;
    }

    Context sContext;
    List<OwnerTripDetail> sData;
    public static final String FB_DATABASE_PATH = "captionSchedule";
    DatabaseReference ref;
    class AllTripHolder extends RecyclerView.ViewHolder {
        TextView tv_StartLocation,tv_EndLocation,tv_StartTime,tv_EndTime,tv_StartDate,tv_NoOfDays;
        public AllTripHolder(View itemView) {
            super(itemView);
            tv_StartLocation =(TextView)itemView.findViewById(R.id.tv_StartLocation);
            tv_EndLocation =(TextView)itemView.findViewById(R.id.tv_endlocation);
            tv_StartTime =(TextView)itemView.findViewById(R.id.tv_StartTime);
            tv_EndTime =(TextView)itemView.findViewById(R.id.tv_EndTime);
            tv_StartDate =(TextView)itemView.findViewById(R.id.tv_tripdate);
            tv_NoOfDays =(TextView)itemView.findViewById(R.id.tv_days);
            ref= FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        }


    }

    @NonNull
    @Override

    public SeeScheduleAdapter.AllTripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(sContext).inflate(R.layout.layout_alltrips,parent,false);
        return new SeeScheduleAdapter.AllTripHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeScheduleAdapter.AllTripHolder holder, int position) {
        holder.tv_StartLocation.setText(sData.get(position).getStartLocation());
        holder.tv_EndLocation.setText(sData.get(position).getEndLocation());
        holder.tv_StartTime.setText(sData.get(position).getTripStartTime());
        holder.tv_EndTime.setText(sData.get(position).getTripEndTime());
        holder.tv_StartDate.setText(sData.get(position). getDate()   );
        holder.tv_NoOfDays.setText(sData.get(position).getNoOfDays());


    }

    @Override
    public int getItemCount() {
        return sData.size();
    }
}
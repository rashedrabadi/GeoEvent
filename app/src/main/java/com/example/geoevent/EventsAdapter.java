package com.example.geoevent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.CartViewHolder> {
    //attributes declaration
    private Context mCtx;
    private List<event> List;

    int pos;
    //end of declaration

    class LID{ // inner class to hols the postion of the card
        event name;
        int position;

        public LID(event name, int position) {
            this.name = name;
            this.position = position;
        }
    } //end of inner class
    ArrayList <LID> l=new ArrayList<>();


    public EventsAdapter(Context mCtx, List<event> list) {//constructor
        this.mCtx = mCtx;
        List = list;
    }//end of constructor

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cardview,null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
            LID l2=new LID(List.get(position),position);
            pos=position;
            l.add(l2);
             holder.eventName.setText(List.get(position).name);
             holder.eventDate.setText(List.get(position).date);
             holder.eventTime.setText(List.get(position).time);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder { //inner class viewholder
        TextView eventName;
        TextView eventTime;
        TextView eventDate;
        Button Details;

        public CartViewHolder(View view) { //inner class constructor
            super(view);
            eventName=view.findViewById(R.id.EventName);
            eventTime=view.findViewById(R.id.Time);
            eventDate=view.findViewById(R.id.Date);
            Details = view.findViewById(R.id.Details);

            Details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent details = new Intent(mCtx, CheckIn.class);
                    for(int i=0;i<List.size();i++)
                    {
                        if(List.get(i).name.equals(eventName.getText())){pos=i; break;}
                    }
                    details.putExtra("description",List.get(pos).description);
                    details.putExtra("name",List.get(pos).name);
                    details.putExtra("time",List.get(pos).time);
                    details.putExtra("date",List.get(pos).date);
                    details.putExtra("sponsers",List.get(pos).sponsers);
                    details.putExtra("place",List.get(pos).place);
                    mCtx.startActivity(details);
                }
            });

            //   FullName.setText(List.get(position).length);

        } //end of inner class constructor
    }//end inner class
}

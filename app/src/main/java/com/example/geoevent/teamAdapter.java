package com.example.geoevent;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class teamAdapter extends RecyclerView.Adapter<teamAdapter.CartViewHolder> {
    //attributes declaration
    private Context mCtx;
    private java.util.List<event> List;

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
    ArrayList<LID> l=new ArrayList<>();


    public teamAdapter(Context mCtx, List<event> list) {//constructor
        this.mCtx = mCtx;
        List = list;
    }//end of constructor

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.teamcardview,null);
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
        TextView going;
        Button delete;

        public CartViewHolder(View view) { //inner class constructor
            super(view);
            eventName=view.findViewById(R.id.EventName);
            eventTime=view.findViewById(R.id.Time);
            eventDate=view.findViewById(R.id.Date);
            delete = view.findViewById(R.id.delete);
            going = view.findViewById(R.id.students);



            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Events");

            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   long num=0;
                    for (DataSnapshot EventSnapshot: snapshot.getChildren()) {
                        if(EventSnapshot.child("name").getValue().equals(eventName.getText().toString())){

                           num=EventSnapshot.child("GoingStudents").getChildrenCount();
                        }

                    }


                    going.setText(String.valueOf(num));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query Q=ref.child("Events").orderByChild("name").equalTo(eventName.getText().toString());

                    Q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();

                        }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("TAG", "onCancelled", error.toException());
                        }
                    });


                }
            });

            //   FullName.setText(List.get(position).length);

        } //end of inner class constructor
    }//end inner class
}

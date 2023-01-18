package com.hall_event_reservation_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hall_event_reservation_system.databinding.ActivityAddEventsPageBinding;
import com.hall_event_reservation_system.databinding.ActivityNavSetScheduleBinding;
import com.hall_event_reservation_system.models.users_add_events_model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_events_page extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    ActivityAddEventsPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_add_events_page);
        binding = ActivityAddEventsPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String[] items =  {"BasketBall","VolleyBall","Badminton","Ping Pong","Chess","Other"};
        String[] time_items =  {"9:00 am","9:30 am","10:00 am","10:30 am","11:00 am","11:30 am","12:00 pm"
        ,"12:30 pm","1:00 pm","1:30 pm","2:00 pm","2:30 am","3:00 am","3:30 pm","4:00 pm","4:30 pm","5:00 pm"
        ,"5:30 pm","6:00 pm"};

      //  autoCompleteTxt = findViewById(R.id.autoCompleteTextView);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        binding.autoCompleteTextView.setAdapter(adapterItems);

        //from time adapter
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item2,time_items);
        binding.fromTime.setAdapter(adapterItems);

        //to time adapter
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item2,time_items);
        binding.toTime.setAdapter(adapterItems);

        binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

              //  Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
    }) ;
        binding.fromTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String time_items = parent.getItemAtPosition(position).toString();

              //  Toast.makeText(getApplicationContext(),"Item: "+time_items,Toast.LENGTH_SHORT).show();
            }
        }) ;
        binding.toTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String time_items = parent.getItemAtPosition(position).toString();

               // Toast.makeText(getApplicationContext(),"Item: "+time_items,Toast.LENGTH_SHORT).show();
            }
        }) ;

        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_data();
                binding.progressCircular.setProgress(View.GONE);
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(add_events_page.this,nav_set_Schedule.class));
                finish();
            }
        });


    }

    private void add_data() {




        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/LLL/yyyy");
        String date = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
        String time = currentTime.format(calendar.getTime());
        String mydate_time = date + " " + time;


        String doc_id = db.collection("events").document().getId();

        String sports = binding.autoCompleteTextView.getText().toString().trim();
        String fullname = binding.fullName.getText().toString().trim();
        String phonenumber = binding.phoneNumber.getText().toString().trim();
        String homeaddress = binding.homeAddress.getText().toString().trim();
        String from_Time = binding.fromTime.getText().toString().trim();
        String to_Time = binding.toTime.getText().toString().trim();
        String Date = binding.date.getText().toString().trim();
        String status = "Pending";


        users_add_events_model usersAddEventsModel = new users_add_events_model(doc_id ,sports,fullname,phonenumber
        ,homeaddress,from_Time,to_Time,Date,mydate_time,status);

        db.collection("my events").document(doc_id).set(usersAddEventsModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(add_events_page.this, "success", Toast.LENGTH_SHORT).show();


                    }
                });
        binding.progressCircular.setProgress(View.VISIBLE);
    }
}
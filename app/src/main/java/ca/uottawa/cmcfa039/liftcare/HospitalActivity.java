package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private ArrayAdapter<String> hospitalAdapter;
    private ArrayList<String> hospitalArrayList = new ArrayList<>();
    private ListView hospitalList;
    private Button hospitalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        Toolbar toolbar = findViewById(R.id.toolbarHospital);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hospitalList = findViewById(R.id.hospitalListView);
        hospitalAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,hospitalArrayList);
        hospitalList.setAdapter(hospitalAdapter);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("/hospitals");
        mDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Hospital hospital = dataSnapshot.getValue(Hospital.class);
                hospitalArrayList.add(hospital.toString());
                hospitalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        hospitalList = findViewById(R.id.hospitalListView);
        hospitalButton = findViewById(R.id.hospitalBtn);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalActivity.this,NewHospitalActivity.class));
            }
        });



    }
}

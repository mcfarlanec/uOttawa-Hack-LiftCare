package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RequestActivity extends AppCompatActivity {

    private EditText patientText;
    private EditText ageText;
    private EditText severityText;
    private EditText hospitalText1;
    private EditText hospitalText2;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;

    private Button requestButton;
    private FirebaseDatabase mDatabase;

    private Hospital[] hospitalArray = new Hospital[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Toolbar toolbar = findViewById(R.id.toolbarRequest);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientText = findViewById(R.id.patientText);
        ageText = findViewById(R.id.ageText);
        severityText = findViewById(R.id.severityText);
        hospitalText1 = findViewById(R.id.hospitalText1);
        hospitalText2 = findViewById(R.id.hospitalText2);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        requestButton = findViewById(R.id.newRequestBtn);
        mDatabase = FirebaseDatabase.getInstance();


        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit() {

        final String name = patientText.getText().toString();
        final String age = ageText.getText().toString();
        final String severity = severityText.getText().toString();
        final String hospital1 = hospitalText1.getText().toString();
        final String hospital2 = hospitalText2.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Please enter a Name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(getApplicationContext(), "Please enter a Age!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(severity)) {
            Toast.makeText(getApplicationContext(), "Please enter a Severity!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(hospital1)) {
            Toast.makeText(getApplicationContext(), "Please enter the first Hospital!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(hospital2)) {
            Toast.makeText(getApplicationContext(), "Please enter the second Hospital!", Toast.LENGTH_LONG).show();
            return;
        }

        if(checkBox1.isChecked() ^ checkBox2.isChecked() ^ checkBox3.isChecked() ^ checkBox4.isChecked()) {

            mDatabase.getReference("/hospitals");
            mDatabase.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                        for(DataSnapshot round2 : postSnapShot.getChildren()) {
                            System.out.println("GOT HERE5555");
                            final Hospital hospital = round2.getValue(Hospital.class);
                            if (hospital.getName().equals(hospital1)) {
                                hospitalArray[0] = hospital;
                                System.out.println("GOT HERE");
                                System.out.println(postSnapShot.getValue(Hospital.class).getName());
                                fill(hospitalArray, age, name, severity);

                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            mDatabase.getReference("/hospitals");
            mDatabase.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                        for(DataSnapshot round2 : postSnapShot.getChildren()) {
                            System.out.println("GOT HERE5555");
                            final Hospital hospital = round2.getValue(Hospital.class);
                            if (hospital.getName().equals(hospital2)) {
                                hospitalArray[1] = hospital;
                                System.out.println("GOT HERE");
                                System.out.println(postSnapShot.getValue(Hospital.class).getName());
                                fill(hospitalArray, age, name, severity);

                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


    }

    private void fill(Hospital[] hArray, String age, String name, String severity) {

        if (hArray[0] != null && hArray[1] != null) {

            Integer ageI = Integer.parseInt(age);
            Double severityD = Double.parseDouble(severity);


            Route r = new Route(hArray[0], hArray[1]);

            Patient p = new Patient();


            if (checkBox1.isChecked()) {
                p = new Patient(name, ageI, 1, severityD);
            }

            if (checkBox2.isChecked()) {
                p = new Patient(name, ageI, 2, severityD);

            }

            if (checkBox3.isChecked()) {
                p = new Patient(name, ageI, 3, severityD);

            }

            if (checkBox4.isChecked()) {
                p = new Patient(name, ageI, 4, severityD);

            }

            Request request = new Request(p, r);
            String path = name + age;
            mDatabase.getReference("/requests").child(path).setValue(request);
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent(RequestActivity.this, HomeActivity.class));


        } else {
            Toast.makeText(getApplicationContext(), "Please check one box!", Toast.LENGTH_LONG).show();
            return;
        }



    }


}

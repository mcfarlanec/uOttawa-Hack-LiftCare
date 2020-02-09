package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.LocaleList;
import android.telephony.mbms.StreamingServiceInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewHospitalActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText latText;
    private EditText longText;
    private FirebaseDatabase mDatabase;
    private Button hospitalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hospital);

        Toolbar toolbar = findViewById(R.id.toolbarNewHospital);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.hospitalNameText);
        latText = findViewById(R.id.latText);
        longText = findViewById(R.id.longText);
        mDatabase = FirebaseDatabase.getInstance();

        hospitalButton = findViewById(R.id.newHospitalBtn);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    public void submit(){
        final String name = nameText.getText().toString();
        final String lat = latText.getText().toString();
        final String lng = longText.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Please enter a Name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(lat)) {
            Toast.makeText(getApplicationContext(), "Please enter a Latitude", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(lng)) {
            Toast.makeText(getApplicationContext(), "Please enter a Longitude!", Toast.LENGTH_LONG).show();
            return;
        }

        Double latD = Double.parseDouble(lat);
        Double lngD = Double.parseDouble(lng);

        Hospital tempHospital = new Hospital(name, latD, lngD);

        mDatabase.getReference("/hospitals").child(name).setValue(tempHospital);
        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
        startActivity(new Intent(NewHospitalActivity.this, HospitalActivity.class));

    }

}

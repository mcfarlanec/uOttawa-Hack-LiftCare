package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView requestList;
    private ArrayList<String> requestArrayList = new ArrayList<>();
    private ArrayList<Request> superRequestArrayList = new ArrayList<>();
    private ArrayAdapter<String> requestAdapter;
    private DatabaseReference mDatabaseRef;
    private Algorithm algo = new Algorithm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestList = findViewById(R.id.requestList);
        requestAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,requestArrayList);
        requestList.setAdapter(requestAdapter);

        requestAdapter.notifyDataSetChanged();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        requestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Request request = superRequestArrayList.get(position);
                Intent i = new Intent(HomeActivity.this, MapActivity.class);
                i.putExtra("latitude1", request.getRoute().getStartPoint().getLatitude());
                i.putExtra("longitude1", request.getRoute().getStartPoint().getLongitude());
                i.putExtra("latitude2", request.getRoute().getEndPoint().getLatitude());
                i.putExtra("longitude2", request.getRoute().getEndPoint().getLongitude());
                i.putExtra("hospital1", request.getRoute().getStartPoint().getName());
                i.putExtra("hospital2", request.getRoute().getEndPoint().getName());
                startActivity(i);
            }
        });

        final NavigationView nav_view = findViewById(R.id.navigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.nav_helicopter) {
                    Toast.makeText(HomeActivity.this, "HELICOPTER", Toast.LENGTH_LONG);
                }

                else if (id == R.id.nav_hospital){
                    startActivity(new Intent(HomeActivity.this,HospitalActivity.class));
                }

                return true;
            }
        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("/requests");
        mDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Request request = dataSnapshot.getValue(Request.class);
                superRequestArrayList = algo.receiveRequest(request);
                requestArrayList = new ArrayList<>();
                for (int i = 0; i < superRequestArrayList.size();i++){
                    requestArrayList.add(superRequestArrayList.get(i).toString());
                }
                requestAdapter.notifyDataSetChanged();

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


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}

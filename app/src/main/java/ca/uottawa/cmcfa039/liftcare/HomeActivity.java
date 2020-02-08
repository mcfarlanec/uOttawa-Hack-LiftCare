package ca.uottawa.cmcfa039.liftcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView requestList;
    private ArrayList<String> requestArrayList = new ArrayList<>();
    private ArrayAdapter<String> requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestList = findViewById(R.id.requestList);
        requestAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,requestArrayList);
        requestList.setAdapter(requestAdapter);

        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");
        requestArrayList.add("hi");

        requestAdapter.notifyDataSetChanged();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView nav_view = findViewById(R.id.navigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.nav_helicopter) {
                    Toast.makeText(HomeActivity.this, "HELICOPTER", Toast.LENGTH_LONG);
                }

                else if (id == R.id.nav_hospital){
                    Toast.makeText(HomeActivity.this, "HOSPITAL", Toast.LENGTH_LONG);
                }

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}

package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener{
    Intent intent;
    SharedPreferences prefs = null;
    private final AppCompatActivity activity = DoctorActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        prefs = getSharedPreferences("com.sample.pawan", MODE_PRIVATE);//shared prefrenses

        Button nursesBtn = (Button) findViewById(R.id.btnNurses);

        String user = prefs.getString("user", "");

        if(user=="nurse"){
            nursesBtn.setVisibility(View.GONE);
        } else {
            nursesBtn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnNurses:
                intent = new Intent(activity, NursesListingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPatients:
                intent = new Intent(activity, PatientActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTests:
                intent = new Intent(activity, TestActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        prefs.edit().putString("user", "").commit();
        prefs.edit().putLong("id", -1).commit();
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

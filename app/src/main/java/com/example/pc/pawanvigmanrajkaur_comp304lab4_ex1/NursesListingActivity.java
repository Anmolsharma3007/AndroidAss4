package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Nurse;

public class NursesListingActivity extends AppCompatActivity {

    private final AppCompatActivity activity = NursesListingActivity.this;
    private DBAdapter db;
    ListView listView;
    List<String> nurses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurses_listing);


        db = new DBAdapter(this);
        listView = (ListView) findViewById(R.id.nursesList);
        db.open();
        final List<Nurse> list = db.getAllNurses();
        db.close();

        for (Nurse nurse : list){
            nurses.add(nurse.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nurses);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nurse selectedTest = list.get(i);
                Toast.makeText(NursesListingActivity.this, selectedTest.toString() , Toast.LENGTH_SHORT).show();
            }
        });
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
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

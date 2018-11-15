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
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Test;

public class TestActivity extends AppCompatActivity {

    private final AppCompatActivity activity = TestActivity.this;
    private DBAdapter db;
    ListView listView;
    List<String> tests = new ArrayList<String>();
    Intent intent;
    List<Test> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        db = new DBAdapter(this);
        listView = (ListView) findViewById(R.id.listTest);

        Button btn = (Button) findViewById(R.id.newTestBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, TestFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        loadListViewData();
    }

    public void loadListViewData(){
        db.open();
        listView.setAdapter(null);
        list = db.getAllTests();
        db.close();
        tests = new ArrayList<String>();
        for (Test test : list){
            tests.add(test.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tests);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Test selectedTest = list.get(i);
                Intent intent = new Intent(activity, TestFormActivity.class);
                intent.putExtra("testId", selectedTest.getTestId());
                startActivity(intent);
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

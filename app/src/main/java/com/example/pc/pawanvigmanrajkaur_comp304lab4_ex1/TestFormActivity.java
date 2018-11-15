package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Test;

public class TestFormActivity extends AppCompatActivity {
    private final AppCompatActivity activity = TestFormActivity.this;
    private DBAdapter db;
    EditText txtTestId;
    EditText txtPatientId;
    EditText txtNurseId;
    EditText txtBPL;
    EditText txtBPH;
    EditText txtTemperature;
    TextView label;
    TextView labelTestId;
    Button saveBtn;
    int editMode = 1;
    Boolean isNewTest = true;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_form);

        initViews();
        db = new DBAdapter(this);
        Intent intent = getIntent();

        int testId = intent.getIntExtra("testId", -1);

        if (testId > 0){
            db.open();
            test = db.getTest(testId);
            updateFields();
            disableEditMode();
            db.close();
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Test nTest = new Test(Integer.parseInt(txtPatientId.getText().toString()),
                        Integer.parseInt(txtNurseId.getText().toString()),
                        txtBPL.getText().toString(),
                        txtBPH.getText().toString(),
                        txtTemperature.getText().toString());
                Long id;
                db.open();
                switch (editMode) {
                    case 0:
                        enableEditMode();
                        break;
                    case 1:
                        if(isNewTest){
                            id = db.insertTest(nTest);
                            Toast.makeText(activity, "New test created successfully!", Toast.LENGTH_SHORT).show();
                            isNewTest = false;
                            test = nTest;
                            nTest.setTestId(id.intValue());
                            updateFields();
                            disableEditMode();
                        } else {
                            nTest.setTestId(test.getTestId());
                            Boolean a = db.updateTest(nTest);
                            if (a){
                                test = nTest;
                                updateFields();
                                disableEditMode();
                                Toast.makeText(activity, "Test updated successfully!", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(activity, "Can not update", Toast.LENGTH_SHORT).show();

                        }
                        break;
                }
                db.close();
            }
        });
    }

    public void initViews(){
        label           = (TextView) findViewById(R.id.testHeadingLabel);
        labelTestId     = (TextView) findViewById(R.id.labelTestId);
        txtTestId       = (EditText) findViewById(R.id.txtTestId);
        txtNurseId      = (EditText) findViewById(R.id.txtNurseId);
        txtPatientId    = (EditText) findViewById(R.id.txtPatientId);
        txtBPL          = (EditText) findViewById(R.id.txtBpl);
        txtBPH          = (EditText) findViewById(R.id.txtBph);
        txtTemperature  = (EditText) findViewById(R.id.txtTemperature);
        saveBtn         = (Button) findViewById(R.id.saveBtn);
    }
    public void updateFields(){
        label.setText("Test Details");
        txtTestId.setText(Integer.toString(test.getTestId()));
        txtPatientId.setText(Integer.toString(test.getPatientId()));
        txtNurseId.setText(Integer.toString(test.getNurseId()));
        txtBPL.setText(test.getBpl());
        txtBPH.setText(test.getBph());
        txtTemperature.setText(test.getTemperature());
        isNewTest = false;
    }

    private void disableEditMode(){
        labelTestId.setVisibility(View.VISIBLE);
        txtTestId.setVisibility(View.VISIBLE);
        txtTestId.setEnabled(false);
        txtPatientId.setEnabled(false);
        txtNurseId.setEnabled(false);
        txtBPL.setEnabled(false);
        txtBPH.setEnabled(false);
        txtTemperature.setEnabled(false);
        saveBtn.setText("Edit");
        editMode = 0;
    }

    private void enableEditMode(){
        label.setText("Updating Test");
        txtPatientId.setEnabled(true);
        txtTestId.setEnabled(true);
        txtNurseId.setEnabled(true);
        txtBPH.setEnabled(true);
        txtBPL.setEnabled(true);
        txtTemperature.setEnabled(true);
        saveBtn.setText("Update");
        editMode = 1;
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

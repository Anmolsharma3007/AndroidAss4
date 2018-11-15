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

import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Patient;

public class PatientFormActivity extends AppCompatActivity {

    private final AppCompatActivity activity = PatientFormActivity.this;
    private DBAdapter db;
    EditText txtPatientId;
    EditText txtFirstname;
    EditText txtLastname;
    EditText txtDepartment;
    EditText txtDoctorId;
    EditText txtRoom;
    TextView label;
    TextView labelPatientId;
    Button saveBtn;
    int editMode = 1;
    Boolean isNewPatient = true;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        initViews();
        db = new DBAdapter(this);
        Intent intent = getIntent();

        int patientId = intent.getIntExtra("patientId", -1);
        if (patientId > 0){
            db.open();
            patient = db.getPatient(patientId);
            updateFields();
            disableEditMode();
            db.close();
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient nPatient = new Patient(txtFirstname.getText().toString(),
                        txtLastname.getText().toString(),
                        txtDepartment.getText().toString(),
                        Integer.parseInt(txtDoctorId.getText().toString()),
                        txtRoom.getText().toString());
                Long id;
                db.open();
                switch (editMode){
                    case 0:
                        enableEditMode();
                        break;
                    case 1:
                        if(isNewPatient){
                            id = db.insertPatient(nPatient);
                            Toast.makeText(activity, "New patient created successfully!", Toast.LENGTH_SHORT).show();
                            isNewPatient = false;
                            patient = nPatient;
                            patient.setPatientId(id.intValue());
                            updateFields();
                            disableEditMode();
                        } else {
                            nPatient.setPatientId(patient.getPatientId());
                            Boolean a = db.updatePatient(nPatient);
                            if (a){
                                updateFields();
                                disableEditMode();
                                Toast.makeText(activity, "Patient updated successfully!", Toast.LENGTH_SHORT).show();
                                patient = nPatient;
                            } else
                                Toast.makeText(activity, "Can not update", Toast.LENGTH_SHORT).show();

                        }
                        break;
                }
                db.close();
            }
        });
    }

    private void updateFields(){
        label.setText("Patient Details");
        txtPatientId.setText(Integer.toString(patient.getPatientId()));
        txtFirstname.setText(patient.getFirstname());
        txtLastname.setText(patient.getLastname());
        txtDepartment.setText(patient.getDepartment());
        txtDoctorId.setText(Integer.toString(patient.getDoctorId()));
        txtRoom.setText(patient.getRoom());
        isNewPatient = false;
    }

    private void initViews(){
        label           = (TextView) findViewById(R.id.patientHeadingLabel);
        labelPatientId  = (TextView) findViewById(R.id.labelPatientId);
        txtPatientId    = (EditText) findViewById(R.id.txtPatientId);
        txtFirstname    = (EditText) findViewById(R.id.txtFirstname);
        txtLastname     = (EditText) findViewById(R.id.txtLastname);
        txtDepartment   = (EditText) findViewById(R.id.txtDepartment);
        txtDoctorId     = (EditText) findViewById(R.id.txtDoctorId);
        txtRoom         = (EditText) findViewById(R.id.txtRoom);
        saveBtn         = (Button) findViewById(R.id.saveBtn);
    }

    private void disableEditMode(){
        labelPatientId.setVisibility(View.VISIBLE);
        txtPatientId.setVisibility(View.VISIBLE);
        txtPatientId.setEnabled(false);
        txtFirstname.setEnabled(false);
        txtLastname.setEnabled(false);
        txtDepartment.setEnabled(false);
        txtDoctorId.setEnabled(false);
        txtRoom.setEnabled(false);
        saveBtn.setText("Edit");
        editMode = 0;
    }

    private void enableEditMode(){
        label.setText("Updating Patient");
        txtPatientId.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtDepartment.setEnabled(true);
        txtDoctorId.setEnabled(true);
        txtRoom.setEnabled(true);
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

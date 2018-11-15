package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Doctor;
import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Nurse;
import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Patient;
import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Test;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;
    private DBAdapter db;
    SharedPreferences prefs = null;
    EditText txtUsername;
    EditText txtPassword;

    private String tag = "checking**********************";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(tag, "********this is on create start*************************** ");
        db = new DBAdapter(this);
        prefs = getSharedPreferences("com.sample.pawan", MODE_PRIVATE);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        Log.d(tag, "************this is on create end *************************** ");
    }

    /////////////////////////////////////////////q

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "this is resume enterance ", Toast.LENGTH_LONG).show();
        Log.d(tag, "this is resume enterance ");

        if (prefs.getBoolean("firstrun", true)) {
            Toast.makeText(this, "in if statement of Resume", Toast.LENGTH_LONG).show();
            Log.d(tag, "this is resume in if statement calling populate ");
            populateData();
            prefs.edit().putBoolean("firstrun", false).commit();
        }

        Toast.makeText(this, "this is resume ending ", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v){
        Boolean error = false;
        String userName = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(userName.length()==0) {
            txtUsername.requestFocus();
            txtUsername.setError("FIELD EMPTY");
            error = true;
        }

        if(password.length()==0) {
            txtPassword.requestFocus();
            txtPassword.setError("FIELD EMPTY");
            error = true;
        }


        //////////////////////////////////////////////////q
        if(error == false){
            db.open();
            long id;
           // id = db.getDoctor(Long.parseLong(userName));
            id = db.getDoctorwithPassword(Long.parseLong(userName),txtPassword.getText().toString());
            Log.d(tag, "*****this is returned id from doctor password method *****"+ id);
            if(id > 0){
                prefs.edit().putString("user", "doctor").commit();
                prefs.edit().putLong("id", id).commit();
                Intent intent = new Intent(activity, DoctorActivity.class);
                startActivity(intent);
            } else {
               // id = db.getNurse(Long.parseLong(userName));
                id = db.getNursewithPassword(Long.parseLong(userName),txtPassword.getText().toString());
                if(id > 0){
                    prefs.edit().putString("user", "nurse").commit();
                    prefs.edit().putLong("id", id).commit();
                    Intent intent = new Intent(activity, DoctorActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Username/Password is incorrect!", Toast.LENGTH_LONG).show();
                }
            }
            db.close();
        }
    }

    public void populateData(){
        long id;
        Doctor doctor1      = new Doctor(1,"Akashdeep", "Singh", "ss", "password");
        Doctor doctor2      = new Doctor(2,"Jack", "Daniel", "s1", "password");
        Nurse  nurse1       = new Nurse(11,"Jhosh", "Mc", "s2", "password");
        Nurse  nurse2       = new Nurse(12,"Luther", "Smith", "s2", "password");
        Nurse  nurse3       = new Nurse(13,"Monica", "Freder", "s2", "password");
        Nurse  nurse4       = new Nurse(14,"Jasmeet", "Jelly", "ss", "password");
        Patient patient1    = new Patient("Raj", "RO", "department", 1, "10A");
        Patient patient2    = new Patient("Dhoni", "Los", "department", 2, "10B");
        Patient patient3    = new Patient("Virat", "Beck", "department", 2, "11L");
        Patient patient4    = new Patient("Luther", "Ross", "department", 1, "12K");
        Patient patient5    = new Patient("Joey", "Bee", "department", 1, "13K");
        Test   test1   = new Test(1,1,"bpl","bph","temprerature");
        Test   test2   = new Test(2,2,"bpl","bph","temprerature");
        db.open();
        id = db.insertDoctor(doctor1);
        id = db.insertDoctor(doctor2);
        id = db.insertNurse(nurse1);
        id = db.insertNurse(nurse2);
        id = db.insertNurse(nurse3);
        id = db.insertNurse(nurse4);
        id = db.insertPatient(patient1);
        id = db.insertPatient(patient2);
        id = db.insertPatient(patient3);
        id = db.insertPatient(patient4);
        id = db.insertPatient(patient5);
        id = db.insertTest(test1);
        id = db.insertTest(test2);
        db.close();
        Toast.makeText(this, Long.toString(id), Toast.LENGTH_LONG).show();
    }
}

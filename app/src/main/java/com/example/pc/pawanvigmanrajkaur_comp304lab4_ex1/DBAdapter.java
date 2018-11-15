package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Doctor;
import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Nurse;
import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Patient;
import com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models.Test;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "Hospital.db";
    static final String DATABASE_TABLE = "doctors";
    static final int DATABASE_VERSION = 2;

    static final String CREATE_DOCTOR_TABLE_SQL =
            "create table doctors (_id integer primary key, " +
                    "firstname text not null, lastname text not null, " +
                    "department text not null, password text not null);";

    static final String CREATE_NURSE_TABLE_SQL =
            "create table nurses (_id integer primary key, " +
                    "firstname text not null, lastname text not null, " +
                    "department text not null, password text not null);";

    static final String CREATE_PATIENT_TABLE_SQL =
            "create table patients (_id integer primary key autoincrement, " +
                    "doctorId integer, room text, " +
                    "firstname text not null, lastname text not null, " +
                    "department text not null);";

    static final String CREATE_TEST_TABLE_SQL =
            "create table tests (_id integer primary key autoincrement, " +
                    "patientId integer, nurseId integer, " +
                    "bpl text not null, bph text not null, " +
                    "temperature text not null);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(CREATE_DOCTOR_TABLE_SQL);
                db.execSQL(CREATE_NURSE_TABLE_SQL);
                db.execSQL(CREATE_TEST_TABLE_SQL);
                db.execSQL(CREATE_PATIENT_TABLE_SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS doctors");
            db.execSQL("DROP TABLE IF EXISTS nurses");
            db.execSQL("DROP TABLE IF EXISTS tests");
            db.execSQL("DROP TABLE IF EXISTS patients");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertDoctor(Doctor doctor)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("_id", doctor.getDoctorId());
        initialValues.put("firstname", doctor.getFirstname());
        initialValues.put("lastname", doctor.getLastname());
        initialValues.put("department", doctor.getDepartment());
        initialValues.put("password", doctor.getPassword());
        return db.insert("doctors", null, initialValues);
    }

    public long insertNurse(Nurse nurse)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("_id", nurse.getNurseId());
        initialValues.put("firstname", nurse.getFirstname());
        initialValues.put("lastname", nurse.getLastname());
        initialValues.put("department", nurse.getDepartment());
        initialValues.put("password", nurse.getPassword());
        return db.insert("nurses", null, initialValues);
    }

    public long insertPatient(Patient patient)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("firstname", patient.getFirstname());
        initialValues.put("lastname", patient.getLastname());
        initialValues.put("department", patient.getDepartment());
        initialValues.put("doctorId", patient.getDoctorId());
        initialValues.put("room", patient.getRoom());
        return db.insert("patients", null, initialValues);
    }
    public boolean updatePatient(Patient patient)
    {
        ContentValues args = new ContentValues();
        args.put("firstname", patient.getFirstname());
        args.put("lastname", patient.getLastname());
        args.put("department", patient.getDepartment());
        args.put("doctorId", patient.getDoctorId());
        args.put("room", patient.getRoom());
        return db.update("patients", args, "_id=" + patient.getPatientId(), null) >0;
    }

    public long insertTest(Test test)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("patientId", test.getPatientId());
        initialValues.put("nurseId", test.getNurseId());
        initialValues.put("bpl", test.getBpl());
        initialValues.put("bph", test.getBph());
        initialValues.put("temperature", test.getTemperature());
        return db.insert("tests", null, initialValues);
    }

    public boolean updateTest(Test test)
    {
        ContentValues args = new ContentValues();
        args.put("patientId", test.getPatientId());
        args.put("nurseId", test.getNurseId());
        args.put("bpl", test.getBpl());
        args.put("bph", test.getBph());
        args.put("temperature", test.getTemperature());
        return db.update("tests", args, "_id=" + test.getTestId(), null) > 0;
    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_EMAIL}, null, null, null, null, null);
    }

    public List<Test> getAllTests(){
        Cursor currentCursor;
        List<Test> list = new ArrayList<Test>();
        currentCursor = db.query("tests", new String[] {"_id", "patientId",
                "nurseId", "bpl", "bph", "temperature"}, null, null, null, null, null);

        if (currentCursor.moveToFirst())
        {
            do {
                Test test = new Test(currentCursor.getInt(0), currentCursor.getInt(1), currentCursor.getInt(2),
                        currentCursor.getString(3), currentCursor.getString(4), currentCursor.getString(5));
                list.add(test);
            } while (currentCursor.moveToNext());
        }
        db.close();
        return list;
    }

    public List<Nurse> getAllNurses(){
        Cursor currentCursor;
        List<Nurse> list = new ArrayList<Nurse>();
        currentCursor = db.query("nurses", new String[] {"_id", "firstname",
                "lastname", "department"}, null, null, null, null, null);

        if (currentCursor.moveToFirst())
        {
            do {
                Nurse nurse = new Nurse(currentCursor.getInt(0), currentCursor.getString(1),
                        currentCursor.getString(2), currentCursor.getString(3));
                list.add(nurse);
            } while (currentCursor.moveToNext());
        }
        db.close();
        return list;
    }

    public List<Patient> getAllPatient(){
        Cursor currentCursor;
        List<Patient> list = new ArrayList<Patient>();
        currentCursor = db.query("patients", new String[] {"_id", "firstname",
                "lastname", "department", "doctorId", "room"}, null, null, null, null, null);

        if (currentCursor.moveToFirst())
        {
            do {
                Patient patient = new Patient(currentCursor.getInt(0), currentCursor.getString(1),
                        currentCursor.getString(2), currentCursor.getString(3),
                        currentCursor.getInt(4), currentCursor.getString(5));
                list.add(patient);
            } while (currentCursor.moveToNext());
        }
        db.close();
        return list;
    }

    public Patient getPatient(int rowId)
    {
        Cursor mCursor =
                db.query(true, "patients", new String[] {"_id", "firstname", "lastname",
                                "department", "doctorId", "room"}, "_id" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();

            Patient patient = new Patient(mCursor.getInt(0), mCursor.getString(1),
                    mCursor.getString(2), mCursor.getString(3),
                    mCursor.getInt(4), mCursor.getString(5));
            return patient;
        } else {
            return null;
        }
    }

    public Test getTest(int rowId)
    {
        Cursor mCursor =
                db.query(true, "tests", new String[] {"_id", "patientId", "nurseId",
                                "bpl", "bph", "temperature"}, "_id=" + rowId, null,
                        null, null, null, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();

            Test test = new Test(mCursor.getInt(0), mCursor.getInt(1), mCursor.getInt(2),
                    mCursor.getString(3), mCursor.getString(4), mCursor.getString(5));
            return test;
        } else {
            return null;
        }
    }

    //---retrieves a particular contact---
    public long getDoctor(long rowId)
    {
        Cursor mCursor =
                db.query(true, "doctors", new String[] {"_id"
                        }, "_id" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            return mCursor.getLong(0);
        } else {
            return -1;
        }
    }


    public long getDoctorwithPassword(long rowId,String password)
    {
        Cursor mCursor =  //db.rawQuery( "select * from doctors where _id = "+rowId+" and password = '"+ password+"'", null );
                db.query(true, "doctors", new String[] {"_id"
                        }, "_id" + "=" + rowId +" AND password = '"+password+"'", null,
                        null, null, null, null);
        Log.d("data adaptor", "*****before if *****");
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            Log.d("data adaptor", "*****this is returned password from adaptor *****");

            return mCursor.getLong(0);
        } else {
            return -1;
        }
    }

    public long getNursewithPassword(long rowId, String password)
    {
        Cursor mCursor =
                db.query(true, "nurses", new String[] {"_id"
                        }, "_id" + "=" + rowId +" AND password = '"+password+"'", null,
                        null, null, null, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            return mCursor.getLong(0);
        } else {
            return -1;
        }
    }



    public long getNurse(long rowId)
    {
        Cursor mCursor =
                db.query(true, "nurses", new String[] {"_id"
                        }, "_id" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            return mCursor.getLong(0);
        } else {
            return -1;
        }
    }

    //---updates a contact---
    public boolean updateContact(long rowId, String name, String email)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}


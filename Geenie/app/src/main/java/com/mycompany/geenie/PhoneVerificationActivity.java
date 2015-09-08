package com.mycompany.geenie;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;


public class PhoneVerificationActivity extends Activity {

    Button buttonSubmit, buttonSubmitOTP;
    EditText textPhoneNo, textEmail, textOTP;
    TextView textViewOTP;

    private static final String TAG = "GenieDatabase";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "genie_db";

    public static final String TABLE_USERS = "users";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE" + TABLE_USERS
                    + "(" +USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_NAME + "TEXT NOT NULL"
                    + USER_PHONE + "TEXT NOT NULL );";
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textEmail = (EditText)findViewById(R.id.editTextEmail);
        buttonSubmitOTP = (Button)findViewById(R.id.buttonSubmitOTP);
        textOTP = (EditText)findViewById(R.id.editTextOTP);
        textViewOTP = (TextView)findViewById(R.id.textViewOTP);

        final Context context = this;

        buttonSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = textPhoneNo.getText().toString();
                String emailId = textEmail.getText().toString();

               /* db.execSQL(CREATE_TABLE_USERS);
                insertUser(emailId, phoneNo);
                String otp =  selectUser(emailId) + "abc";
*/
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, "123456", null, null);
                    Toast.makeText(getApplicationContext(), "OTP is sent !",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "OTP sending failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                textOTP.setVisibility(View.VISIBLE);
                buttonSubmitOTP.setVisibility(View.VISIBLE);
                textViewOTP.setVisibility(View.VISIBLE);
            }
        });

        buttonSubmitOTP.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (textOTP.getText().toString().equals("123456")) {
                    Intent intent = new Intent(context, SearchCategoryActivity.class);
                    startActivity(intent);
                }

                else{

                    Toast.makeText(getApplicationContext(),
                            "Incorrect OTP",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
/*
    public void createTable(){
        db.execSQL(CREATE_TABLE_USERS);
    }*/

  /*  public void insertUser(String userName, String userPhone){
        SQLiteDatabase sqLiteDatabase = null;
        db.execSQL("INSERT INTO " + TABLE_USERS + " VALUES('" + userName + "', '" + userPhone + "');");
    }

    public String selectUser(String userName){
        String uPhone = "";
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = db.rawQuery("SELECT " + USER_ID + " FROM " + TABLE_USERS + " WHERE " + USER_NAME + " =" + userName,null);

        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    uPhone = cursor.getString(cursor.getColumnIndex("USER_PHONE"));
                }while (cursor.moveToNext());
            }
        }
        return  uPhone;
    }*/
}

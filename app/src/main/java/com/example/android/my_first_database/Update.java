package com.example.android.my_first_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DB.ContactDao;
import RV.DATA;

public class Update extends AppCompatActivity  {
String name,phone;
EditText Phone;
EditText Name;
Button UpdateBtn;
ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Phone = findViewById(R.id.editTxtPhone);
        Name = findViewById(R.id.editTxtName);
        UpdateBtn= findViewById(R.id.UpdateButton);
    }

    public void UpdateClicked(View view) {
        name = Name.getText().toString();
        phone = Phone.getText().toString();




        finish();
    }

    //TODO onClickListener to get Strings and then update the table


}
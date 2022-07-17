package com.example.android.my_first_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DB.ContactDao;
import DB.ContactDatabase;
import RV.Adp;
import RV.DATA;
import RV.VH;

public class MainActivity extends AppCompatActivity implements Adp.OnUserClicked {
    private Adp adapter;
    private ArrayList<DATA> dataArrayList = new ArrayList<>();
    private RecyclerView RV;
    private ContactDatabase Database;
    private String Name, Phone;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private VH vh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RVsetUp();
        DbsetUp();
        started();
      adapter.register(this);
       SwipeToDelete();
    }

    private void SwipeToDelete() {
        new  ItemTouchHelper( new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // delete element

                    executorService.execute(() -> {

                        int pos = viewHolder.getAdapterPosition();
                        Database.contactDao().deleteContact(adapter.ReturnData(pos));

                    });

            }
        }).attachToRecyclerView(RV);
    }

    private void DbsetUp() {
        Database = ContactDatabase.getInstance(this);

    }

    private void RVsetUp() {
        RV = findViewById(R.id.RV);
        adapter = new Adp(dataArrayList);
        LinearLayoutManager L1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(L1);
        RV.setAdapter(adapter);
    }

    public void started() {
        Database.contactDao().getAll().observe(this, data -> {
            dataArrayList.clear();
            dataArrayList.addAll(data);
            adapter.notifyDataSetChanged();
        });

    }

    public void AddClicked(View view) {
        AddContact();

    }

    public void AddContact() {
        AlertDialog.Builder nameDialog = new AlertDialog.Builder(MainActivity.this);
        nameDialog.setTitle("Name");

        final EditText NameTXT = new EditText(MainActivity.this);
        NameTXT.setInputType(InputType.TYPE_CLASS_TEXT);
        nameDialog.setView(NameTXT);

        // positive
        nameDialog.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Name = NameTXT.getText().toString();
                dialogInterface.cancel();
                //  second dialog

                AlertDialog.Builder phoneDialog = new AlertDialog.Builder(MainActivity.this);
                phoneDialog.setTitle("Phone");

                final EditText PhoneTXT = new EditText(MainActivity.this);
                PhoneTXT.setInputType(InputType.TYPE_CLASS_PHONE);
                phoneDialog.setView(PhoneTXT);

                // second positive
                phoneDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // add to table;
                        Phone = PhoneTXT.getText().toString();

                        executorService.execute(() -> {
                            Database.contactDao().insertContact(new DATA(R.drawable.var, Phone, Name));

                        });


                        dialogInterface.cancel();
                    }
                });

                phoneDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                phoneDialog.show();


            }
        });

        nameDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        nameDialog.show();

        Name = "";
        Phone = "";
    }

    @Override
    public void OnUserLongClicked(DATA user) {
        //TODO: Update Dialog
        int pos = vh.getAdapterPosition();

        Intent intent= new Intent(this,Update.class);
        startActivity(intent);
        Toast.makeText(this, "UPDATE CONTACT", Toast.LENGTH_SHORT).show();
    }



}


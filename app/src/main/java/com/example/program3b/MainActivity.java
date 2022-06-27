package com.example.program3b;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Name);
        contact = findViewById(R.id.Contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checki = DB.insertuserdata(nameTXT,contactTXT,dobTXT);
                if(checki==true)
                    Toast.makeText(MainActivity.this,"New Entry Inserted", Toast.LENGTH_LONG);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checki = DB.updateuserdata(nameTXT,contactTXT,dobTXT);
                if(checki==true)
                    Toast.makeText(MainActivity.this,"New Entry Updated", Toast.LENGTH_LONG);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checki = DB.deletedata(nameTXT);
                if(checki==true)
                    Toast.makeText(MainActivity.this,"Entry Deleted", Toast.LENGTH_LONG);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No entry", Toast.LENGTH_LONG);
                    return;
                }
               StringBuffer buffer = new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contaact :"+res.getString(1)+"\n");
                    buffer.append("DOB :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
}
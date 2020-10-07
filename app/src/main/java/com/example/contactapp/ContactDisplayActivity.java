package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDisplayActivity extends AppCompatActivity {
    Button btnEditContact;
    public TextView nameText, numberText;
    public Menu menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        btnEditContact = findViewById(R.id.btnEDIT);

        nameText = findViewById(R.id.nameShow);
        numberText = findViewById(R.id.numberShow);

       // String newName = (String) getIntent().getSerializableExtra("newName"); // Anothr Methode
        //   String newName = getIntent().getStringExtra("newName");
      //  nameText.setText(newName);
      //  String newNumber = getIntent().getStringExtra("newNumber");
      //  numberText.setText(newNumber);

        btnEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openEditActivity();
            }
        });
    }

    public void openEditActivity(){
        Intent ContactEditActivity = new Intent(getBaseContext(), com.example.contactapp.ContactEditActivity.class);
         ContactEditActivity.putExtra("newNumber",numberText.getText().toString());
         ContactEditActivity.putExtra("newName",nameText.getText().toString());
        startActivityForResult(ContactEditActivity, 1);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAppel:
               // Toast.makeText(getBaseContext(), "Hello Appel :" + numberText.getText().toString(), Toast.LENGTH_LONG).show();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel: "+ numberText.getText().toString()));
                startActivity(callIntent);
                return true;
            case R.id.menuMessage:
             //   Toast.makeText(getBaseContext(), "Hello Message", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("tel: "+ numberText.getText()));
                intent.putExtra("sms_body", "Type Message Here...");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK ){
            if(data != null){
                numberText.setText(data.getStringExtra("NewNumber"));
                nameText.setText(data.getStringExtra("NewName"));
            }
        }
    }



}
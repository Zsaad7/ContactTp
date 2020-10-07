package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactEditActivity extends AppCompatActivity {
    public TextView showNumberPhone;
    public EditText EditNameContact;
    public Button BackBtn, ModifyBtn;
    public String fullNewNumber="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);


        BackBtn = findViewById(R.id.backButton);
        showNumberPhone = findViewById(R.id.numberPanel);
        showNumberPhone.setText(getIntent().getStringExtra("newNumber"));
        EditNameContact = findViewById(R.id.EditTextContact);
        EditNameContact.setText(getIntent().getStringExtra("newName"));



        final int[] DIGIT_BUTTONS = new int[]{ R.id.button0, R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};

        for (final int id: DIGIT_BUTTONS) {
            final Button b = findViewById(id);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fullNewNumber = fullNewNumber +b.getText().toString();
                    if(fullNewNumber.startsWith("0") ){
                           if(showNumberPhone.length()<11){
                               showNumberPhone.setText(fullNewNumber);
                               BackBtn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       fullNewNumber = fullNewNumber.substring(0, fullNewNumber.length() - 1);
                                       showNumberPhone.setText(fullNewNumber);
                                   }
                               });
                           }else{
                               Toast.makeText(getBaseContext(), "Please, the number phone must contain 10 DIGITS.", Toast.LENGTH_LONG).show();
                           }
                    }else{
                        Toast.makeText(getBaseContext(), "Please, enter a valide number starts with 07 or 06.", Toast.LENGTH_LONG).show();
                    }
                        }

            });
        }
    //Pour l'instant Affichage du Contenu avec TOAST, Name+NumberPhone
        ModifyBtn = findViewById(R.id.btnModify);
        ModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getBaseContext(), "Name : "+ EditNameContact.getText().toString() + " Phone Number : " + fullNewNumber, Toast.LENGTH_LONG).show();
                Intent redirectIntent = new Intent(getBaseContext(), ContactDisplayActivity.class);
                    redirectIntent.putExtra("NewName", EditNameContact.getText().toString());
                    redirectIntent.putExtra("NewNumber", showNumberPhone.getText().toString());
                    setResult(RESULT_OK, redirectIntent);

            finish();
            }
        });

    }




}
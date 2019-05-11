package com.example.movieapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText email,number,bdate,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void newRegistration(View view){


        EditText email = (EditText) findViewById(R.id.emailr);
        EditText number = (EditText) findViewById(R.id.numberr);
        EditText bdate = (EditText) findViewById(R.id.bdater);
        EditText password = (EditText) findViewById(R.id.passwordr);

        String emailid = email.getText().toString();
        String number1 = email.getText().toString();
        String birthdate = bdate.getText().toString();
        String password1 = password.getText().toString();

        MyDBHandler dbHandler = new MyDBHandler(this);
        User user = new User(emailid, number1, birthdate, password1);
        dbHandler.addHandler(user);
        email.setText("");
        number.setText("");
        bdate.setText("");
        password.setText("");

        Context context = getApplicationContext();
        CharSequence text = "Registered!!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

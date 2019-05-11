package com.example.movieapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkLogin(View view){

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        String email1="";
        String password1="";


        String emailid = email.getText().toString();
        String password2 = password.getText().toString();

        MyDBHandler dbHandler = new MyDBHandler(this);
        User user =
                dbHandler.findHandler(email.getText().toString());
        if (user != null) {
            email1=user.getEmail();
            password1=user.getPassword();
            email.setText("");
            password.setText("");
        } else {
            email.setText("");
            password.setText("");
        }

        if((emailid.equals(email1))&&(password2.equals(password1))){
            Log.i(TAG, "checkLogin: "+emailid+" "+email1+" "+password2+" "+password1);
            Intent intent = new Intent(this, MovieHomepage.class);
            startActivity(intent);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Login Failed";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }

    public void goTORegistration(View view){

        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }

}

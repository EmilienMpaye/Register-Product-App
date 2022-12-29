package com.example.registerproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signup,signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Register Activity");

        username = findViewById(R.id.username);
        password = findViewById(R.id.pin);
        repassword = findViewById(R.id.re_pin);
        signup = findViewById(R.id.btn_signUp);
        signIn =findViewById(R.id.btn_signIn);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")|| repass.equals(""))

                    Toast.makeText(MainActivity.this, "Please enter correct identity", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {


                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();



                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "user already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
      signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent( getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }

}
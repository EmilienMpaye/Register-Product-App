package com.example.registerproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btn_login;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Existing user");
        username =(EditText) findViewById(R.id.username1);
        password =(EditText) findViewById(R.id.pin1);
        btn_login =(Button) findViewById(R.id.btn_login);
        DB =new DBHelper(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pinn =password.getText().toString();

                if(user.equals("")||pinn.equals(""))
                    Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass =DB.checkusernamepassword(user,pinn);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
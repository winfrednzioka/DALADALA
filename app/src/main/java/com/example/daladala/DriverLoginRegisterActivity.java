package com.example.daladala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {
    private Button DriverLoginButton;
    private Button DriverRegisterButton;
    private TextView DriverRegisterLink;
    private TextView DriverStatus;
    private EditText EmailDriver;
    private EditText PasswordDriver;

    private ProgressDialog LoadingBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);


        mAuth = FirebaseAuth.getInstance();


        DriverLoginButton = findViewById(R.id.Driver_Login_button);
        DriverRegisterButton= findViewById(R.id.Driver_Register_button);
        DriverRegisterLink = findViewById(R.id.Driver_Register_Link);
        DriverStatus = findViewById(R.id.Driver_Status);
        EmailDriver = findViewById(R.id.Email_Driver);
        PasswordDriver = findViewById(R.id.Password_Driver);
        LoadingBar = new ProgressDialog(this);


        DriverRegisterButton.setVisibility(View.INVISIBLE);
        DriverRegisterButton.setEnabled(false);

        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriverLoginButton.setVisibility(View.INVISIBLE);
                DriverRegisterLink.setVisibility(View.INVISIBLE);
                DriverStatus.setText("Register_Driver");


                DriverRegisterButton.setVisibility(View.VISIBLE);
                DriverRegisterButton.setEnabled(true);
            }
        });
        DriverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = EmailDriver.getText().toString();
                String Password = PasswordDriver.getText().toString();

                RegisterDriver (Email, Password);

            }
        });

        DriverLoginButton.setOnClickListener(v -> {
            String Email = EmailDriver.getText().toString();
            String Password = PasswordDriver.getText().toString();

            SigninDriver(Email, Password);
        });

    }

    private void SigninDriver(String Email, String password)
    {
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            LoadingBar.setTitle("Driver Signin");
            LoadingBar.setMessage("Please wait, while we are checking your credentials");
            LoadingBar.show();


            mAuth.signInWithEmailAndPassword(Email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)

                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Logged in Successfully...",Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                                Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriverMapsActivity.class);
                                startActivity(driverIntent);

                            }

                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Login Unsuccessful, Please try Again...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();


                            }

                        }
                    });
        }



    }

    private void RegisterDriver (String Email, String password)
    {
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {

            LoadingBar.setTitle("Driver Registration");
            LoadingBar.setMessage("Please wait, while we register your data");
            LoadingBar.show();

            mAuth.createUserWithEmailAndPassword(Email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)

                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Successful...",Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                                Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriverMapsActivity.class);
                                startActivity(driverIntent);

                            }

                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Registration Unsuccessful, Please try Again...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }

                        }
                    });

        }
    }
}

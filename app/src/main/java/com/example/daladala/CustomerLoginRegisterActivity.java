package com.example.daladala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
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

public class CustomerLoginRegisterActivity extends AppCompatActivity
{
    private Button CustomerLoginButton;
    private Button CustomerRegisterButton;
    private TextView CustomerRegisterLink;
    private TextView CustomerStatus;
    private EditText EmailCustomer;
    private EditText PasswordCustomer;

    private ProgressDialog LoadingBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        mAuth = FirebaseAuth.getInstance();

        CustomerLoginButton= (Button) findViewById(R.id.Customer_Login_button);
        CustomerRegisterButton = (Button) findViewById(R.id.Register_Customer_button);
        CustomerRegisterLink = (TextView) findViewById(R.id.Register_Customer_Link);
        CustomerStatus = (TextView) findViewById(R.id.Customer_Status);
        EmailCustomer = (EditText) findViewById(R.id.Email_Customer);
        PasswordCustomer = (EditText) findViewById(R.id.Password_Customer);
        LoadingBar = new ProgressDialog(this);



        CustomerRegisterButton.setVisibility(View.INVISIBLE);
        CustomerRegisterButton.setEnabled(false);

        CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerLoginButton.setVisibility(View.INVISIBLE);
                CustomerRegisterLink.setVisibility(View.INVISIBLE);
                CustomerStatus.setText("Register_Customer");


                CustomerRegisterButton.setVisibility(View.VISIBLE);
                CustomerRegisterButton.setEnabled(true);
            }
        });

        CustomerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = EmailCustomer.getText().toString();
                String Password = PasswordCustomer.getText().toString();

                RegisterCustomer(Email, Password);

            }
        });

        CustomerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String Email = EmailCustomer.getText().toString();
                String Password = PasswordCustomer.getText().toString();

                SigninCustomer (Email, Password);

            }
        });

    }

    private void SigninCustomer(String Email, String Password)
    {
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(Password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {

            LoadingBar.setTitle("Customer Signin");
            LoadingBar.setMessage("Please wait, while we are checking your credentials");
            LoadingBar.show();


            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)

                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Logged in Successfuly...",Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Login Unsuccessful, Please try Again...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }

                        }
                    });
        }

    }


    private void RegisterCustomer (String Email, String password)
    {
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();

        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {

            LoadingBar.setTitle("Customer Registration");
            LoadingBar.setMessage("Please wait, while we register your data");
            LoadingBar.show();


            mAuth.createUserWithEmailAndPassword(Email, password)
                    .addOnCompleteListener (new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)

                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register Successfuly...",Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }

                            else

                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Registration Unsuccessful, Please try Again...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }

                        }
                    });
        }
    }
}





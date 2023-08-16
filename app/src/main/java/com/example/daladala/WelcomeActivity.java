package com.example.daladala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity
{
    private Button WelcomeDriverButton;
    private Button WelcomeCustomerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        WelcomeCustomerButton = (Button) findViewById(R.id.Welcome_Customer_button);
        WelcomeDriverButton = (Button) findViewById(R.id.Welcome_Driver_button);

        WelcomeCustomerButton.setOnClickListener(v -> {
            Intent LoginRegisterCustomerIntent = new Intent(WelcomeActivity.this,CustomerLoginRegisterActivity.class);
            startActivity(LoginRegisterCustomerIntent);
            finish();
        });
        WelcomeDriverButton.setOnClickListener(v -> {
            Intent LoginRegisterDriverIntent = new Intent(WelcomeActivity.this,DriverLoginRegisterActivity.class);
            startActivity(LoginRegisterDriverIntent);
            finish();
        });
    }
}

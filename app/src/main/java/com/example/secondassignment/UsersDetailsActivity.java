package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UsersDetailsActivity extends AppCompatActivity {

    ImageView detailImage;
    TextView detailName, txtDob, txtGender, txtCountry, txtPhone, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_details);

        Intent intent = getIntent();

        String name = intent.getStringExtra("NAME");
        String dob = intent.getStringExtra("DOB");
        String gender = intent.getStringExtra("Gender");
        String country = intent.getStringExtra("Country");
        String phone = intent.getStringExtra("Phone");
        String email = intent.getStringExtra("Email");
        String image = intent.getStringExtra("Image");
        int img = Integer.parseInt(image);


        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        txtDob = findViewById(R.id.txtDob);
        txtGender = findViewById(R.id.txtGender);
        txtCountry = findViewById(R.id.txtCountry);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);

        detailImage.setImageResource(img);
        detailName.setText(name);
        txtDob.setText(dob);
        txtGender.setText(gender);
        txtCountry.setText(country);
        txtEmail.setText(email);
        txtPhone.setText(phone);
    }
}

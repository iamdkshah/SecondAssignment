package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.secondassignment.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editName, selectDob, editPhone, editEmail;
    private AutoCompleteTextView inputImage;
    private RadioGroup RG;
    private Spinner onSpinner;
    private Button btnSubmit, btnView;


    private List<User> userList = new ArrayList<>();
    String[] user ={"deepak", "sumit", "sunil", "kushal"};
    String name, gender, dob, country, phone, email, image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        selectDob = findViewById(R.id.selectDob);
        RG = findViewById(R.id.RG);
        onSpinner = findViewById(R.id.onSpinner);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        inputImage = findViewById(R.id.inputImage);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);


        setOnSpinnerSelect();
        setOnGenderSelect();
        setOnAutoCompleteTextView();

        selectDob.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }


    private void setOnSpinnerSelect(){
        onSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "On Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnDobSelect(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/" + year;
                selectDob.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setOnGenderSelect(){
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.RBMale) {
                    gender = "Male";
                } else if (checkedId == R.id.RBFemale) {
                    gender = "Female";
                } else if (checkedId == R.id.RBOther) {
                    gender = "Other";
                }
            }
        });
    }

    private void setOnAutoCompleteTextView(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.text_values_layout,user);

        inputImage.setAdapter(arrayAdapter);
        inputImage.setThreshold(1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.selectDob){
            setOnDobSelect();
        }

        if (v.getId() == R.id.btnSubmit){
            name = editName.getText().toString().trim();
            phone = editPhone.getText().toString().trim();
            email = editEmail.getText().toString().trim();
            image = inputImage.getText().toString().trim();
            dob = selectDob.getText().toString();

            String uri = "@drawable/"+image;
            int resID = getResources().getIdentifier(uri, null, getPackageName());

            if (validation()){
                userList.add(new User(name,phone,gender,country,email,image,resID));
                Toast.makeText(this, "New Contact added", Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.btnView){
            Intent intent = new Intent(MainActivity.this, userListActivity.class);
            intent.putExtra("User_data", (Serializable) userList);
            startActivity(intent);
        }
    }

    private boolean validation(){
        if(TextUtils.isEmpty(name)){
            editName.setError("Name must not be empty!");
            editName.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(dob)){
            selectDob.setError("");
            Toast.makeText(this, "Date of birth must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Please select your gender!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "Please select a country!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(country.equals("Select Your Country")){
            Toast.makeText(this, "Please select a country!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phone)){
            editPhone.setError("Phone must not be empty!");
            editPhone.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(email)){
            editEmail.setError("Email Address must not be empty!");
            editEmail.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(image)){
            inputImage.setError("Image Name must not be empty!");
            inputImage.requestFocus();
            return false;
        }

        return true;
    }
}

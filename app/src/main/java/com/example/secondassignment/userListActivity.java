package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.secondassignment.model.User;

import java.util.List;

public class userListActivity extends AppCompatActivity {
    List<User> user_data;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.rvUsers);

        Intent intent = getIntent();
        user_data = (List<User>) intent.getSerializableExtra("User_data");

        UserAdapter uAdapter = new UserAdapter(user_data, userListActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(uAdapter);
    }
}

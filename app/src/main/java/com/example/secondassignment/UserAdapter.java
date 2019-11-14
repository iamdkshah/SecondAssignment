package com.example.secondassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondassignment.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersHolder>{

    List<User> userList;
    private Context context;
    User user;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_layout, parent, false);
        UsersHolder usersHolder = new UsersHolder(itemView);
        return usersHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, final int position) {

        user = userList.get(position);
        holder.textUser.setText(user.getName());
        holder.imageUser.setImageResource(user.getImage());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UsersDetailsActivity.class);
                user = userList.get(position);
                intent.putExtra("NAME", user.getName());
                intent.putExtra("DOB", user.getDob());
                intent.putExtra("Gender",user.getGender());
                intent.putExtra("Country",user.getCountry());
                intent.putExtra("Phone", user.getPhone());
                intent.putExtra("Email",user.getEmail());
                intent.putExtra("Image",String.valueOf(user.getImage()));
                System.out.println(user.getImage());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder{

        ImageView imageUser;
        TextView textUser;
        RelativeLayout relativeLayout;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);

            imageUser = itemView.findViewById(R.id.imageUser);
            textUser = itemView.findViewById(R.id.textUser);
            relativeLayout = itemView.findViewById(R.id.layoutUser);
        }
    }

}

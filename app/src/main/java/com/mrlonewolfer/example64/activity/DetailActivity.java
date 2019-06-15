package com.mrlonewolfer.example64.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrlonewolfer.example64.R;
import com.mrlonewolfer.example64.adapter.UserListCustomAdapter;
import com.mrlonewolfer.example64.config.AppDbConnection;
import com.mrlonewolfer.example64.dao.UserDao;
import com.mrlonewolfer.example64.model.UserBean;


import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements UserListCustomAdapter.OnUserClickListner  {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private List<UserBean> listusers;
    private  UserBean userBeanFile;
    private UserDao userDaoFile;
    UserListCustomAdapter userListCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView=findViewById(R.id.listUser);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        AppDbConnection appDatabaseCon= Room.databaseBuilder(this,AppDbConnection.class,"user_db")
                .allowMainThreadQueries()
                .build();

        userDaoFile=appDatabaseCon.userDao();
        listusers=userDaoFile.getAllUserBeans();
        Toast.makeText(this, "Size is :  "+listusers.size(), Toast.LENGTH_SHORT).show();
        userListCustomAdapter= new UserListCustomAdapter(listusers,this);
        recyclerView.setAdapter(userListCustomAdapter);

    }


    @Override
    public void onUserClick(UserBean userBean, String myAction) {
        if(myAction.equals("Delete")){
            userDaoFile.deleteUserdb(userBean);
            userListCustomAdapter.notifyDataSetChanged();
            Toast.makeText(this, userBean.getName()+" Is Deleted", Toast.LENGTH_SHORT).show();
        }
        if(myAction.equals("Edit")){

            Intent intent=new Intent(DetailActivity.this, MainActivity.class);
            intent.putExtra("myParcebaleData",userBean);
            startActivity(intent);
            this.finish();
        }
    }
}

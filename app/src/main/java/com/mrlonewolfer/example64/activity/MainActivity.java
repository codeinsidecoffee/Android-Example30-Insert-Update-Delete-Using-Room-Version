package com.mrlonewolfer.example64.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrlonewolfer.example64.R;
import com.mrlonewolfer.example64.adapter.UserListCustomAdapter;
import com.mrlonewolfer.example64.config.AppDbConnection;
import com.mrlonewolfer.example64.dao.UserDao;
import com.mrlonewolfer.example64.model.UserBean;

import java.util.List;

/*
Write a program you have to create android application for demonstrate
the insert,  update, delete and select the value.

  •You have to take this kind of fields (Id, Name, Email, Phone and Address).

  •You have to take two buttons on main activity (Display, Add New).

  •On Insert button click user will insert record in database and
            inserted data will be  display in display activity.

  •On Display button click user get all record on another Display activity.
        When user  click on one record Context menu will display

  •In context menu user have get options like (Edit, Delete).
        If user click on Edit    record that  record will in edit mode and
            user will update the record and
                display in   display Activity.

        If user click on delete record then
            record will be delete from display activity


 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtName,edtPhone,edtEmail,edtAdress,edtId;
    Button btnSubmit,btnView;

    private List<UserBean> userBeanList;
    private UserBean userBeanFile;
    private UserDao userDaoFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtName=findViewById(R.id.edtName);
        edtPhone=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmail);
        edtAdress=findViewById(R.id.edtAddress);
        edtId=findViewById(R.id.edtId);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnView=findViewById(R.id.btnView);
        btnSubmit.setOnClickListener(this);
        btnView.setOnClickListener(this);

        AppDbConnection appDbConnection= Room.databaseBuilder(this,AppDbConnection.class,"user_db")
                .allowMainThreadQueries()
                .build();

        userDaoFile=appDbConnection.userDao();

        Intent intent=getIntent();
        userBeanFile=intent.getParcelableExtra("myParcebaleData");
        if(userBeanFile!=null){


            btnSubmit.setText("Update");
            edtId.setVisibility(View.VISIBLE);
            edtId.setText(userBeanFile.getId()+"");
            edtName.setText(userBeanFile.getName());
            edtPhone.setText(userBeanFile.getPhone()+"");
            edtEmail.setText(userBeanFile.getEmail());
            edtAdress.setText(userBeanFile.getAddress());

        }

        
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSubmit){
            if(userBeanFile==null){
                userBeanFile=new UserBean();
                userBeanFile.setName(edtName.getText().toString());
                userBeanFile.setEmail(edtEmail.getText().toString());
                userBeanFile.setAddress(edtAdress.getText().toString());
                userBeanFile.setPhone(Integer.parseInt(edtPhone.getText().toString()));


                userDaoFile.insertUserdb(userBeanFile);
                Toast.makeText(this, "Name : "+userBeanFile.getName()+"\n" +
                        "Email : "+userBeanFile.getEmail()+"\n" +
                        "Address: "+userBeanFile.getAddress(), Toast.LENGTH_LONG).show();
            }
            else{
                userBeanFile.setName(edtName.getText().toString());
                userBeanFile.setEmail(edtEmail.getText().toString());
                userBeanFile.setAddress(edtAdress.getText().toString());
                userBeanFile.setPhone(Integer.parseInt(edtPhone.getText().toString()));
                userDaoFile.updateUserdb(userBeanFile);
                Toast.makeText(this, "Data Updated Successfully ", Toast.LENGTH_SHORT).show();
            }
            resetAllData(v);

        }
        if(v.getId()==R.id.btnView){

            Toast.makeText(this, "Get All list of User", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,DetailActivity.class);
            startActivity(intent);

        }
    }

    private void resetAllData(View v) {
        edtId.setText("");
        edtId.setVisibility(View.GONE);
        edtName.setText("");
        edtPhone.setText("");
        edtEmail.setText("");
        edtAdress.setText("");
        btnSubmit.setText("Submit");

        userDaoFile=null;
    }


}

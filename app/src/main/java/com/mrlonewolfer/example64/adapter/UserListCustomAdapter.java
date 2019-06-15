package com.mrlonewolfer.example64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mrlonewolfer.example64.R;
import com.mrlonewolfer.example64.activity.DetailActivity;
import com.mrlonewolfer.example64.model.UserBean;

import java.util.ArrayList;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListCustomAdapter extends  RecyclerView.Adapter<UserListCustomAdapter.userViewHolder>{

    private  OnUserClickListner listner;
    private List<UserBean> userBeanArrayList;
    UserBean userBeanFile;

    public UserListCustomAdapter(List<UserBean> userBeanArrayList,OnUserClickListner listner) {
        this.listner = listner;
        this.userBeanArrayList = userBeanArrayList;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row_iteam,viewGroup,false);
        userViewHolder viewHolder = new userViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        final UserBean users=userBeanArrayList.get(position);
            holder.txtId.setText(users.getId()+"");
            holder.txtName.setText(users.getName());
            holder.txtEmail.setText(users.getEmail());
            holder.txtAddress.setText(users.getAddress());
            holder.txtPhone.setText(users.getPhone()+"");
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onUserClick(users,"Delete");
                    userBeanArrayList.remove(users);
                }
            });
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onUserClick(users,"Edit");
                }
            });

    }

    @Override
    public int getItemCount() {
        return userBeanArrayList.size();
    }


    public  interface  OnUserClickListner{
        void  onUserClick(UserBean userBean,String myAction);

    }

    public class userViewHolder extends RecyclerView.ViewHolder{
        TextView txtId,txtName,txtPhone,txtEmail,txtAddress;
        ImageView imgDelete,imgEdit;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);

                txtId=itemView.findViewById(R.id.txtId);
                txtName=itemView.findViewById(R.id.txtName);
                txtEmail=itemView.findViewById(R.id.txtEmail);
                txtPhone=itemView.findViewById(R.id.txtPhone);
                txtAddress=itemView.findViewById(R.id.txtAdress);
                imgEdit=itemView.findViewById(R.id.imgEdit);
                imgDelete=itemView.findViewById(R.id.imgDelete);

        }
    }
}

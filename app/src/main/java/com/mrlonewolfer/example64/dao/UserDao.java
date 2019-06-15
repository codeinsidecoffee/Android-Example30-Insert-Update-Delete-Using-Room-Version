package com.mrlonewolfer.example64.dao;

import com.mrlonewolfer.example64.model.UserBean;

import java.util.List;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert
    void insertUserdb(UserBean userBean);

    @Update
    void updateUserdb(UserBean userBean);

    @Delete
    void deleteUserdb(UserBean userBean);

    @Query("select * from userBean")
    List<UserBean> getAllUserBeans();


}

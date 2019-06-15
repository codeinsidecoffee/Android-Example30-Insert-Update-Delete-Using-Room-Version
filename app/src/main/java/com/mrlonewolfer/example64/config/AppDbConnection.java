package com.mrlonewolfer.example64.config;

import com.mrlonewolfer.example64.dao.UserDao;
import com.mrlonewolfer.example64.model.UserBean;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserBean.class},version = 1,exportSchema = false)
public abstract class AppDbConnection extends RoomDatabase {
    public abstract UserDao userDao();

}

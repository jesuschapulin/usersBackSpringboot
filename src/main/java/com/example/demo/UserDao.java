package com.example.demo;

import com.example.demo.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
   List<User> findAll();
   int setNewUser(User User);
   List<User> findByLogin(String login);
   List<User> findByName(String words);
   List<User> findByStatus(String status);
   List<User> findByDate(String date1,String date2);
   List<User> AccessByLogin(String login,String pass);
   int deleteUser(String login);
   int updateUser(User User);
   
   String getUsersADEAredis();
   String getPapers();
}
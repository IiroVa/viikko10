package com.example.viikko9;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserStorage {

    private ArrayList <User> Users = new ArrayList<>();

    private static UserStorage userStorage = null;
    private UserStorage(){

    }

    public static UserStorage getInstance(){

        if(userStorage==null){
            userStorage = new UserStorage();

        }

        return userStorage;
    }

    public ArrayList<User> getUsers(){

        return Users;
    }

    public void addUser(User user){
        Users.add(user);

    }
    public void saveUsers (Context context){
        try {
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput("users.data", Context.MODE_PRIVATE));
            userWriter.writeObject(Users);
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Käyttäjien tallennus ei onnistunut.");
        }
    }
    public void loadUsers(Context context) {
        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput("users.data"));
            Users = (ArrayList<User>) userReader.readObject();
            userReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        }


    }







}

package com.example.mycasinofx.Model.database;

public class DAOPattern {

    static Data data = new DatabaseManager();

    public static void getBalance(){
        System.out.println(data.getData());
    }

    public static void updateBalance(){
        System.out.println(data.getData());
    }

    public static void getData(){
        System.out.println(data.getData());
    }
}

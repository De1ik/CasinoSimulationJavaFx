package com.example.mycasinofx.Model.database;

public interface Data {
    void connect();
    void executeQuery(String query);
    void close();
    String getData();
}

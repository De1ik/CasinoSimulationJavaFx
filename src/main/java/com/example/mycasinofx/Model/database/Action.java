package com.example.mycasinofx.Model.database;

@FunctionalInterface
interface Action {
    void perform(String functionName);
}
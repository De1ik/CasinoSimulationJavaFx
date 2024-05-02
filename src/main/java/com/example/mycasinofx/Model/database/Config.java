package com.example.mycasinofx.Model.database;
import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    protected String DB_HOST =  Dotenv.configure().load().get("DB_HOST"); //"35.228.113.233";
    protected String DB_PORT = "3306";
    protected String DB_USER = "root";
    protected String DB_PASS = "delik_develop_stu_fiit";
    protected String DB_NAME = "casinojavafx";

}

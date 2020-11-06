package me.hize;

import co.aikar.idb.DB;
import co.aikar.idb.Database;
import co.aikar.idb.DatabaseOptions;
import co.aikar.idb.PooledDatabaseOptions;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;



public class test {

    public static SQL sql;
    public static DatabaseOptions options;
    public static Database db;

    public static void main(String[] args){
        File file = new File( "log.db");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        options = DatabaseOptions.builder().sqlite(file.toString()).build();
        db = PooledDatabaseOptions.builder().options(options).createHikariDatabase();
        DB.setGlobalDatabase(db);

        try {
            DB.executeUpdate("CREATE TABLE IF NOT EXISTS log(id INTEGER, user TEXT, guild TEXT, channel TEXT, message TEXT, date TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = new SQL();
        JDA jda = null;

        try {
            jda = JDABuilder.createDefault("MzY3NzIwOTYwODIxOTUyNTEz.Wd5Q1A.oxC--KZs6ppWs9iZRYCcfYJitXk")
                    .setAutoReconnect(true)
                    .setBulkDeleteSplittingEnabled(false)
                    .addEventListeners(new ee())
                    .setContextEnabled(false)
                    .build().awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        System.out.println("Connected on "+jda.getGuilds().size()+" Guilds");
        System.out.println("Waiting for message...");


    }
}

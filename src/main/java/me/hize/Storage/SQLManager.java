package me.hize.Storage;

import co.aikar.idb.DB;
import co.aikar.idb.Database;
import co.aikar.idb.DatabaseOptions;
import co.aikar.idb.PooledDatabaseOptions;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SQLManager {

    private static DatabaseOptions options;
    private static Database db;

    public SQLManager(){}

    public static void setup(){
        File file = new File( "settings.db");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        options = DatabaseOptions.builder().sqlite(file.toString()).build();
        db = PooledDatabaseOptions.builder().options(options).createHikariDatabase();
        DB.setGlobalDatabase(db);

        try {
            DB.executeUpdate("CREATE TABLE IF NOT EXISTS settings(token TEXT, prefix TEXT, ownerid TEXT, coownerid TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

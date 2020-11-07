package me.hize.Storage;

import co.aikar.idb.DB;

import java.sql.SQLException;
import java.util.List;

public class SQLQuery {

    public SQLQuery() {}

    public List<Integer> selectAllID() throws SQLException {
        List<Integer> results;
        results = DB.getFirstColumnResults("SELECT id FROM questions");

        return results;
    }

    public String getSetting(String key) throws SQLException {
        return DB.getFirstColumn("SELECT "+key+" FROM settings");
    }

    public int insert(String user, String guild, String channel, String message, String date) throws SQLException {
        Integer index;


        index = DB.getFirstColumn("SELECT max(id) from log");

        if (index == null) {
            index = 1;
        } else {
            index += 1;
        }

        DB.executeInsert("INSERT INTO log(id, user, guild, channel, message, date) VALUES(?, ?, ?, ?, ?, ?)",
                index, user, guild, channel, message, date);


        return index;
    }

    public void update(int id, String newQuestion, String newReponse) throws SQLException {
        DB.executeUpdate("UPDATE questions SET question = ?, reponse = ? WHERE id = ?",
                newQuestion, newReponse, id);

    }

    public void updateSetting(String key, String value) throws SQLException {
        DB.executeUpdate("UPDATE settings SET ? = ?", key, value);

    }

    public void delete(int id) throws SQLException {
        DB.executeUpdate("DELETE from questions WHERE id = ?", id);

    }

}
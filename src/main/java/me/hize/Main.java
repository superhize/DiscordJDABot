package me.hize;

import me.hize.Manager.DiscordManager;
import me.hize.Storage.SQLManager;
import me.hize.Storage.SQLQuery;


public class Main {

    public static SQLQuery sql;
    public static DiscordManager discordManager = new DiscordManager();
    public static Settings settings;

    public static void main(String[] args) {
        SQLManager.setup();
        sql = new SQLQuery();
        settings = new Settings();
        discordManager.init(settings);


    }
}

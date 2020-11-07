package me.hize;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;


public class Config {

    public boolean GENERAL__ENABLED;
    public int GENERAL__DELAY;
    public int GENERAL__MINPLAYER;
    public int GENERAL__TIMEOUT;

    public List<String> RECOMPENSES__CMD;


    /**
     * Initialise the Configuration wrapper with values.
     *
     * @param plugin the Plugin instance to use
     */
    public Config() {
        plugin.saveDefaultConfig();
        InputStream stream = plugin.getClass().getResourceAsStream("/config.yml");
        LocaleUtils.mergeYaml(stream, new File(plugin.getDataFolder(), "config.yml"));

        stream = plugin.getClass().getResourceAsStream("/config.yml");
        FileConfiguration config = plugin.getConfig();
        InputStreamReader streamReader = new InputStreamReader(stream);
        FileConfiguration sourceConfig = YamlConfiguration.loadConfiguration(streamReader);

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String fieldName = field.getName().toLowerCase().replace("__", ".");
            Object targetValue = config.get(fieldName, null);

            if (targetValue == null) {
                targetValue = sourceConfig.get(fieldName);
            }

            try {
                field.set(this, targetValue);
            } catch (IllegalAccessException ignored) {
                Bukkit.getLogger().warning("TimedQuestion failed to set the " + fieldName + " configuration value");
            }
        }

        try {
            stream.close();
            streamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("TimedQuestion could not close stream");
        }
    }

    public String set(Plugin plugin, ConfigValue path, Object value) {
        FileConfiguration config = plugin.getConfig();
        Field field;
        try {
            field = this.getClass().getDeclaredField(path.toString());
        } catch (NoSuchFieldException e) {
            return "TimedQuestion cannot get the declaredField " + path.toString();
        }
        if (field != null) {
            field.setAccessible(true);

            String fieldName = field.getName().toLowerCase().replace("__", ".");
            config.set(fieldName, value);

            try {
                field.set(this, value);
            } catch (IllegalAccessException e) {
                return "TimedQuestion cannot set the declaredField " + path.toString() + " to " + value;
            }

            plugin.saveConfig();
            return "La valeur " + path.toString() + " a correctement été définie sur " + value;
        }
        return "An unknow error occured, please contact an administrator.";
    }

    public List<String> getReward(Plugin plugin){
        FileConfiguration config = plugin.getConfig();
        Field field;
        try {
            field = this.getClass().getDeclaredField(ConfigValue.RECOMPENSES_CMD.toString());
        } catch (NoSuchFieldException e){
            return null;
        }
        if (field != null) {
            field.setAccessible(true);

            String fieldName = field.getName().toLowerCase().replace("__", ".");
            return config.getStringList(fieldName);

        }
        return null;
    }

}

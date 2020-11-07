package me.hize;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class Settings {

    public String TOKEN;
    public String PREFIX;
    public String OWNER__ID;
    public String CO__OWNER__ID;


    public Settings() {

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String fieldName = field.getName().toLowerCase().replace("__", "");
            Object targetValue = null;
            try {
                targetValue = Main.sql.getSetting(fieldName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                field.set(this, targetValue);
            } catch (IllegalAccessException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    public String set(SettingsValue path, Object value) {
        Field field;
        try {
            field = this.getClass().getDeclaredField(path.toString());
        } catch (NoSuchFieldException e) {
            return "Cannot get the declaredField " + path.toString();
        }
        if (field != null) {
            field.setAccessible(true);

            String fieldName = field.getName().toLowerCase().replace("__", ".");
            try {
                Main.sql.updateSetting(fieldName, (String) value);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                field.set(this, value);
            } catch (IllegalAccessException e) {
                return "Cannot set the declaredField " + path.toString() + " to " + value;
            }

            return "La valeur " + path.toString() + " a correctement été définie sur " + value;
        }
        return "An unknow error occured, please contact an administrator.";
    }
}

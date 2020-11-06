package me.hize;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.hize.Utils.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;


public class DiscordManager {

    public static JDA jda;

    public void init(String token){
        try {
            jda = JDABuilder.createDefault(token)
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

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix(Constants.PREFIX);
        builder.setHelpWord("helpme");

        CommandClient client = builder.build();

        jda.addEventListener(client);

    }
}

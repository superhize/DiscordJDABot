package me.hize;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

public class ee extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        System.out.println(event.getAuthor().getName()+ ": Got message from "+event.getChannel().getName()+" in server "+event.getGuild().getName()+": "+event.getMessage().getContentRaw());
        try {
            test.sql.insert(event.getAuthor().getName(),
                    event.getGuild().getName(),
                    event.getChannel().getName(),
                    event.getMessage().getContentRaw(),
                    new Date().toInstant().atZone(ZoneId.of("Europe/Paris")).toString());
            System.out.println("Added entry.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

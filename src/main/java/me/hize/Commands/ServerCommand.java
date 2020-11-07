package me.hize.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class ServerCommand extends Command {

    public ServerCommand(){
        this.name = "serverinfo";
        this.help = "Informations sur le serveur.";
    }

    @Override
    protected void execute(CommandEvent event) {
        int guildMemberCount = event.getGuild().getMemberCount();
        int channelCount = event.getGuild().getChannels().size();


        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setAuthor(event.getGuild().getName());
        eb.setThumbnail("https://pbs.twimg.com/profile_images/997603567728054274/Ukn5dAco_400x400.jpg");
        eb.addField("Fondateur: ", event.getGuild().getOwner().getEffectiveName(), true);
        eb.addField("Channels: ", ""+channelCount, true);
        eb.setFooter("Invite: https://discord.gg/uJ9Jz2ZdeC");

        event.getChannel().sendMessage(eb.build()).queue();
    }
}

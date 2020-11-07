package me.hize.Events;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {


    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        Role visiteur = e.getGuild().getRoleById("774350486656647169");
        Role membre = e.getGuild().getRoleById("774350522710229044");
        Role lock = e.getGuild().getRoleById("774419555342745630");

        boolean hasVisiteur = e.getMember().getRoles().contains(visiteur);
        boolean hasLock = e.getMember().getRoles().contains(lock);

        if (e.getMessageId().equals("774272203217829949") && hasVisiteur && e.getReactionEmote().getName().equals("\u2705")) {

            e.getGuild().removeRoleFromMember(e.getMember().getId(), visiteur).complete();
            e.getGuild().addRoleToMember(e.getMember().getId(), membre).complete();

        } else if (e.getMessageId().equals("774272203217829949") && hasLock && e.getReactionEmote().getName().equals("\u2705")) {

            e.getGuild().removeRoleFromMember(e.getMember().getId(), lock).complete();
            e.getMember().getUser().openPrivateChannel().queue(ev -> ev.sendMessage("La prochaine fois, n'enlevez pas votre réaction....").queue());

        }
    }

    @Override
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent e) {
        Role visiteur = e.getGuild().getRoleById("774350486656647169");
        Role lock = e.getGuild().getRoleById("774419555342745630");
        Role membre = e.getGuild().getRoleById("774350522710229044");

        boolean hasVisiteur = e.getMember().getRoles().contains(visiteur);
        boolean hasMember = e.getMember().getRoles().contains(membre);

        if (e.getMessageId().equals("774272203217829949") && !hasVisiteur && hasMember && e.getReactionEmote().getName().equals("\u2705")) {

            e.getGuild().addRoleToMember(e.getMember().getId(), lock).complete();
            e.getMember().getUser().openPrivateChannel().queue(ev -> ev.sendMessage("Il est important que vous acceptiez notre règlement si vous voulez continuez à utiliser notre discord.").queue());
        }

    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Role visiteur = e.getGuild().getRoleById("774350486656647169");
        if (!e.getMember().getRoles().contains(visiteur))
            e.getGuild().addRoleToMember(e.getMember().getId(), visiteur).complete();
    }

}

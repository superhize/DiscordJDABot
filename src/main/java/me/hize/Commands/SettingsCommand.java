package me.hize.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.Permission;

public class SettingsCommand extends Command {

    private EventWaiter waiter;

    public SettingsCommand(EventWaiter waiter){
        this.name = "setting";
        this.help = "Manage settings";
        this.botPermissions = new Permission[]{Permission.MANAGE_SERVER};
        this.guildOnly = false;

        this.waiter = waiter;
    }

    @Override
    protected void execute(CommandEvent event) {

    }



}





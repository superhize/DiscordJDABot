package me.hize.Manager;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.hize.Commands.ServerCommand;
import me.hize.Commands.SettingsCommand;
import me.hize.Events.EventListener;
import me.hize.Settings;
import me.hize.Utils.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;


public class DiscordManager {

    public static JDA jda;

    public void init(Settings settings){
        try {
            jda = JDABuilder.create(intents)
                    .setToken(settings.TOKEN)
                    .setAutoReconnect(true)
                    .setBulkDeleteSplittingEnabled(false)
                    .addEventListeners(new EventListener())
                    .setContextEnabled(false)
                    .build().awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setPrefix(settings.PREFIX);
        builder.setHelpWord(Constants.HELPWORD);
        builder.setOwnerId(settings.OWNER__ID);
        builder.setCoOwnerIds(settings.CO__OWNER__ID);
        builder.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");
        builder.addCommands(
                new SettingsCommand(waiter),
                new ServerCommand()
        );


        CommandClient client = builder.build();

        jda.addEventListener(client);

    }
    private final EnumSet<GatewayIntent> intents = EnumSet.of(
            GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_BANS,
            GatewayIntent.GUILD_EMOJIS,
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.GUILD_MESSAGE_REACTIONS,
            GatewayIntent.GUILD_EMOJIS
    );
}

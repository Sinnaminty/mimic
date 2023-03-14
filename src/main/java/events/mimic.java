package events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mimic {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("token.txt");
        Scanner s = new Scanner(f);
        final String TOKEN = s.next();
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);

        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new GeneralEventListener())
                .build();


     //     jda.upsertCommand("about","Information about M1MiC").setGuildOnly(true).queue();
     //   jda.upsertCommand("parrot", "copycat").setGuildOnly(true).queue();
       // jda.upsertCommand("ram", "ram sombody :3").setGuildOnly(true).queue();
        jda.updateCommands().addCommands(
                Commands.slash("ram", "om nom :3")
                        .addOption(OptionType.USER, "user", "The user with the delicious ram")
                        .addOption(OptionType.INTEGER, "ramsticks", "The number of ram sticks I should eat"),
                Commands.slash("parrot", "copies everything you say"),
                Commands.slash("about","a lil information about mimic"),
                Commands.slash("bark", "speak thru")
                        .addOption(OptionType.STRING, "message", "what needs to be repeated")
        ).queue();
    }
}

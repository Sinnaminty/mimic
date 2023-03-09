import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Scanner;

public class ass {
    public static void main(String[] args) {
        Scanner s = new Scanner("token.txt");
        final String TOKEN =s.nextLine();
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new GeneralEventListener())
                .build();


        jda.upsertCommand("about","Information about M1MiC").setGuildOnly(true).queue();
        jda.upsertCommand("parrot", "copycat").setGuildOnly(true).queue();
        jda.upsertCommand("com", "hehe").setGuildOnly(true).queue();
    }
}

//this class is called whenever a slash command is used
package events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class GeneralEventListener extends ListenerAdapter {
    ArrayList<String> parrotList = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot())
            return;

        Message message = event.getMessage();
        String content = message.getContentRaw();
        User user = event.getAuthor();
        MessageChannel channel = event.getChannel();

        System.out.println(user.getName() + ": " + content);

        if(parrotList.contains(user.getId())){
            channel.sendMessage(content).queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        System.out.println("Slash command called by " + event.getUser().getName());
        switch(event.getName()) {
            case "parrot":
                parrotFunc(event);
                break;
            case "about":
                aboutFunc(event);
                break;
            case "ram":
                ramFunc(event);
                break;
            case "bark":
                barkFunc(event);
                break;

        }
    }
    public void parrotFunc(SlashCommandInteractionEvent event){
        if(parrotList.contains(event.getUser().getId())){
            parrotList.remove(event.getUser().getId());
            event.reply("No longer parroting " + event.getUser().getName()).queue();
        }else{
            parrotList.add(event.getUser().getId());
            event.reply("Squawk! Now parroting " + event.getUser().getName()).queue();
        }
    }
    public void aboutFunc(SlashCommandInteractionEvent event){
        event.reply("M1MiC\nborn on 12/12/22\nmade by sin").queue();
    }
    public void ramFunc(SlashCommandInteractionEvent event){
        try {
            int ramSticks = event.getOption("ramsticks").getAsInt();
            User rammd = event.getOption("user").getAsUser();
            event.reply(String.format("*Eats %d stick(s) of %s's RAM.*", ramSticks, rammd.getName())).queue();
        }catch(Exception e){
            System.out.println(e);
            errorFunc(event);
        }
    }
    public void barkFunc(SlashCommandInteractionEvent event){
        try {
            event.reply(event.getOption("message").getAsString()).queue();
        }catch(Exception e){
            errorFunc(event);
        }
    }
    public void errorFunc(SlashCommandInteractionEvent event){
        event.reply("Something went wrong!").queue();
    }
}

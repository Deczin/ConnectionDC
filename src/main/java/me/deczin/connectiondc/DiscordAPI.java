package me.deczin.connectiondc;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.Color;
import java.time.Instant;

public class DiscordAPI {

    public static void sendMessage(String channelId, String message) {
        var jda = ConnectionDC.getInstance().getJda();
        if (jda == null) return;

        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel == null) return;

        channel.sendMessage(message).queue();
    }

    public static void sendBasicEmbed(
            String channelId,
            String title,
            int color,
            String description,
            String thumbnailUrl,
            String footer
    ) {
        var jda = ConnectionDC.getInstance().getJda();
        if (jda == null) return;

        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel == null) return;

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle(title);
        embed.setColor(new Color(color));
        embed.setDescription(description);

        if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            embed.setThumbnail(thumbnailUrl);
        }

        if (footer != null && !footer.isEmpty()) {
            embed.setFooter(footer);
        }

        embed.setTimestamp(Instant.now());

        channel.sendMessageEmbeds(embed.build()).queue();
    }

    public static void sendChestLogEmbed(
            String channelId,
            String player,
            String inventoryType,
            String action,
            String item,
            int amount,
            int x,
            int y,
            int z
    ) {
        var jda = ConnectionDC.getInstance().getJda();
        if (jda == null) return;

        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel == null) return;

        int color = action.equalsIgnoreCase("Adicionou") ? 0x00ff00 : 0xff0000;

        String itemLower = item.toLowerCase();

        EmbedBuilder embed = new EmbedBuilder();

        embed.setColor(new Color(color));

        embed.setAuthor(
                "📦 Log de Baú - " + player + "\nTipo: " + inventoryType,
                null,
                "https://minotar.net/helm/" + player + "/100.png"
        );

        embed.setThumbnail("https://minecraftitemids.com/item/128/" + itemLower + ".png");

        embed.setDescription(
                "\n\n**Ação:** `" + action + "`\n\n" +
                "**Item:** `" + amount + "x " + item + "`\n\n" +
                "**Local:** `" + x + ", " + y + ", " + z + "`\n\n"
        );

        embed.setFooter("ChestLogger");
        embed.setTimestamp(Instant.now());

        channel.sendMessageEmbeds(embed.build()).queue();
    }
}
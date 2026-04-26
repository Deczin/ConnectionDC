package me.deczin.connectiondc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class ConnectionDC extends JavaPlugin {

    private static ConnectionDC instance;
    private JDA jda;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        String token = getConfig().getString("discord.token");

        try {
            jda = JDABuilder.createDefault(token).build();
            getLogger().info("ConnectionDC conectado ao Discord!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        if (jda != null) {
            jda.shutdown();
        }
    }

    public static ConnectionDC getInstance() {
        return instance;
    }

    public JDA getJda() {
        return jda;
    }
}

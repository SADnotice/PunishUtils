package com.wizard.alexjd.punish;

import com.wizard.alexjd.punish.commands.CommandPunir;
import com.wizard.alexjd.punish.listeners.ListenerInventory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getCommand("punir").setExecutor(new CommandPunir());

        Bukkit.getServer().getPluginManager().registerEvents(new ListenerInventory(), this);

    }

    @Override
    public void onDisable() {



    }

    public static Core getInstance(){
        return getPlugin(Core.class);
    }

}
